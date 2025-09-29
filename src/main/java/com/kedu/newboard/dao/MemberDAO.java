package com.kedu.newboard.dao;

import com.kedu.newboard.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    private final SqlSessionTemplate mybatis;

    public void register(MemberDTO registerInfo) {
        mybatis.insert("Member.register", registerInfo);
    }

    public MemberDTO login(MemberDTO loginInfo) {
        return mybatis.selectOne("Member.login", loginInfo);
    }
}
