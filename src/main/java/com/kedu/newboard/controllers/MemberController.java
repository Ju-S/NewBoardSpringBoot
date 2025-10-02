package com.kedu.newboard.controllers;

import com.kedu.newboard.dto.MemberDTO;
import com.kedu.newboard.services.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody MemberDTO registerInfo) {
        memberService.register(registerInfo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<MemberDTO> me(HttpSession session) {
        String loginId = (String)session.getAttribute("loginId");
        if(loginId != null) {
            return ResponseEntity.ok(memberService.getById(loginId));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/checkDuplicateId")
    public ResponseEntity<Boolean> checkDuplicateId(@RequestParam String id) {
        boolean result = memberService.checkDuplicateId(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<Void> updateMember( @RequestBody MemberDTO dto) {
        memberService.updateMember(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(HttpSession session) {
        String loginId = (String)session.getAttribute("loginId");
        if(loginId != null){
            memberService.deleteById(loginId);
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }
}
