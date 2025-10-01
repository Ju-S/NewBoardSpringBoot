package com.kedu.newboard.controllers;

import com.kedu.newboard.config.BoardConfig;
import com.kedu.newboard.dto.BoardDTO;
import com.kedu.newboard.dto.ReplyDTO;
import com.kedu.newboard.services.BoardService;
import com.kedu.newboard.services.ReplyService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;

    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<Map<String, Object>> getList(@RequestParam long curpage,
                                                  @RequestParam(defaultValue = "", required = false) String searchQuery) {
        Map<String, Object> response = new HashMap<>();

        List<BoardDTO> boardList = boardService.getList(curpage, searchQuery);
        long maxPage = boardService.getMaxPage(searchQuery);

        response.put("boardList", boardList);
        response.put("itemPerPage", BoardConfig.ITEM_PER_PAGE);
        response.put("pagePerNav", BoardConfig.PAGE_PER_NAV);
        response.put("maxPage", maxPage);

        return ResponseEntity.ok(response);
    }

    // 단일 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();

        BoardDTO selectedItem = boardService.getById(id);
        List<ReplyDTO> replyList = replyService.getList(id);

        response.put("boardDetail", selectedItem);
        response.put("replyList", replyList);

        return ResponseEntity.ok(response);
    }

    // 게시글 등록
    @PostMapping
    public ResponseEntity<Void> posting(@RequestBody BoardDTO dto,
                                        HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");

        if (loginId != null) {
            dto.setWriter(loginId);
            boardService.posting(dto);
        }

        return ResponseEntity.ok().build();
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id,
                                           HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");

        if (loginId != null) {
            if (boardService.getById(id).getWriter().equals(loginId)) {
                boardService.deleteById(id);
            }
        }

        return ResponseEntity.ok().build();
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@RequestBody BoardDTO dto,
                                           @PathVariable long id,
                                           HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");

        if (loginId != null) {
            if (boardService.getById(dto.getId()).getWriter().equals(loginId)) {
                boardService.updateById(dto);
            }
        }

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> error() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
