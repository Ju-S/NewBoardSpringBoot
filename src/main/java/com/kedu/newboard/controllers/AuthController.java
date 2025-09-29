package com.kedu.newboard.controllers;

import com.kedu.newboard.dto.MemberDTO;
import com.kedu.newboard.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody MemberDTO loginInfo) {
        return ResponseEntity.ok(memberService.login(loginInfo));
    }

    @GetMapping
    public ResponseEntity<Void> logout() {

        return ResponseEntity.ok().build();
    }
}
