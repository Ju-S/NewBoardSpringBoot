package com.kedu.newboard.services;

import com.kedu.newboard.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberDAO memberDAO;

}
