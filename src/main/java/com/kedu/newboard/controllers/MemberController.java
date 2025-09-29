package com.kedu.newboard.controllers;

import com.kedu.newboard.dto.MemberDTO;
import com.kedu.newboard.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
