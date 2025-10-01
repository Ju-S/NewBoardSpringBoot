package com.kedu.newboard.controllers;

import com.kedu.newboard.dto.ReplyDTO;
import com.kedu.newboard.services.ReplyService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody ReplyDTO dto,
                                       HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");

        if (loginId != null) {
            dto.setWriter(loginId);
            replyService.insert(dto);
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id,
                                       @RequestBody ReplyDTO modifyInfo,
                                       HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");

        if (loginId != null) {
            if (replyService.getById(id).getWriter().equals(loginId)) {
                replyService.update(modifyInfo);
            }
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestBody long id,
                                       HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");

        if (loginId != null) {
            if (replyService.getById(id).getWriter().equals(loginId)) {
                replyService.delete(id);
            }
        }

        return ResponseEntity.ok().build();
    }
}
