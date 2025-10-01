package com.kedu.newboard.controllers;

import com.kedu.newboard.dto.BoardDTO;
import com.kedu.newboard.services.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<BoardDTO>> getList(@RequestParam long curpage,
                                                  @RequestParam(defaultValue = "", required = false) String searchQuery,
                                                  @RequestParam(defaultValue = "", required = false) String category) {
        List<BoardDTO> list = boardService.getList(curpage, searchQuery, category);

        return ResponseEntity.ok(list);
    }

    // 단일 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getById(@PathVariable long id) {
        BoardDTO selectedItem = boardService.getById(id);
        return ResponseEntity.ok(selectedItem);
    }

    // 게시글 등록
    @PostMapping
    public ResponseEntity<Void> posting(@RequestBody BoardDTO dto) {
        boardService.posting(dto);
        return ResponseEntity.ok().build();
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id,
                                           HttpSession session) {
        String loginId = (String)session.getAttribute("loginId");
        boardService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@RequestBody BoardDTO dto,
                                           @PathVariable long id,
                                           HttpSession session) {
        String loginId = (String)session.getAttribute("loginId");
        boardService.updateById(dto);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> error() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
