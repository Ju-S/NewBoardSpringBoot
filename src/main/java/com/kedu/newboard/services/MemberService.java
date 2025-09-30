package com.kedu.newboard.services;

import com.kedu.newboard.dao.MemberDAO;
import com.kedu.newboard.dto.MemberDTO;
import com.kedu.newboard.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDAO memberDAO;

    public boolean login(MemberDTO loginInfo) {
        loginInfo.setPw(SecurityUtil.encrypt(loginInfo.getPw()));
        return memberDAO.login(loginInfo) != null;
    }

    public void register(MemberDTO registerInfo) {
        registerInfo.setPw(SecurityUtil.encrypt(registerInfo.getPw()));
        memberDAO.register(registerInfo);
    }

    public void updateMember(MemberDTO updateInfo) {
        memberDAO.updateMember(updateInfo);
    }

    public void deleteById(String id){
        memberDAO.deleteById(id);
    }

    // ID 중복체크
    public boolean checkDuplicateId(String id){
        return memberDAO.getById(id) == null;
    }

    // getById - Me 호출시
    public MemberDTO getById(String id){
        return memberDAO.getById(id);
    }
}
