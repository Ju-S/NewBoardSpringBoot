package com.kedu.newboard.services;

import com.kedu.newboard.dao.ReplyDAO;
import com.kedu.newboard.dto.ReplyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyDAO replyDAO;

    public List<ReplyDTO> getList(long boardId) {
        return replyDAO.getList(boardId);
    }

    public ReplyDTO getById(long id) {
        return replyDAO.getById(id);
    }

    public void insert(ReplyDTO dto) {
        replyDAO.insert(dto);
    }

    public void delete(long targetId) {
        replyDAO.delete(targetId);
    }

    public void update(ReplyDTO dto) {
        replyDAO.update(dto);
    }
}
