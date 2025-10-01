package com.kedu.newboard.dao;

import com.kedu.newboard.dto.ReplyDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {

    private final SqlSessionTemplate mybatis;

    // 게시글 번호에 따른 댓글 조회
    public List<ReplyDTO> getList(long boardId) {
        return mybatis.selectList("Reply.getList", boardId);
    }

    // 댓글 ID로 조회
    public ReplyDTO getById(long id) {
        return mybatis.selectOne("Reply.getById", id);
    }

    // 댓글 입력
    public void insert(ReplyDTO dto) {
        mybatis.insert("Reply.insert", dto);
    }

    // 댓글 수정
    public void update(ReplyDTO dto) {
        mybatis.update("Reply.updateById", dto);
    }

    // 댓글 삭제
    public void delete(long targetId) {
        mybatis.delete("Reply.deleteById", targetId);
    }
}
