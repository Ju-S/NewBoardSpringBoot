package com.kedu.newboard.controllers;

import com.kedu.newboard.dto.MemberDTO;
import com.kedu.newboard.services.MemberService;
import com.kedu.newboard.utils.JWTUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final JWTUtil jwt;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberDTO loginInfo, HttpSession session) {
        MemberDTO dto = memberService.login(loginInfo);
        if (dto.getId() != null) {
            String token = jwt.createToken(dto.getId());
            return ResponseEntity.ok(token);
//            session.setAttribute("loginId", loginInfo.getId());
//            System.out.println(loginInfo.getId());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public ResponseEntity<Void> test(HttpSession session) {
        System.out.println(session.getAttribute("loginId"));
        return ResponseEntity.ok().build();
    }
}
