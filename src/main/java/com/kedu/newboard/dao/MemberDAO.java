package com.kedu.newboard.dao;

import com.kedu.newboard.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDAO {

    private final SqlSessionTemplate mybatis;

    public MemberDTO login(MemberDTO loginInfo) {
        return mybatis.selectOne("Member.login", loginInfo);
    }

    public void register(MemberDTO registerInfo) {
        mybatis.insert("Member.register", registerInfo);
    }

    public void updateMember(MemberDTO updateInfo) {
        mybatis.update("Member.updateById", updateInfo);
    }

    public void deleteById(String id) {
        mybatis.delete("Member.deleteById", id);
    }

    // ID 중복확인
    public boolean checkDuplicateId(String id){
        return mybatis.selectOne("Member.checkDuplicateId", id);
    }
}
