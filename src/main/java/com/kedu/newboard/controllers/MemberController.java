package com.kedu.newboard.controllers;

import com.kedu.newboard.dto.MemberDTO;
import com.kedu.newboard.services.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
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

    @GetMapping("/checkDuplicateId")
    public ResponseEntity<Boolean> checkDuplicateId(@RequestParam String id) {
        boolean result = memberService.checkDuplicateId(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable String id, @RequestBody MemberDTO updateInfo) {
        memberService.updateMember(updateInfo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id, HttpSession session) {
        String loginId = (String)session.getAttribute("loginId");
        if(id.equals(loginId)){
            memberService.deleteById(id);
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }
}
