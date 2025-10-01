package com.kedu.newboard.services;

import com.kedu.newboard.dao.BoardDAO;
import com.kedu.newboard.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kedu.newboard.config.BoardConfig.ITEM_PER_PAGE;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDAO boardDAO;

    // 페이지에 해당하는 게시글 목록 조회
    public List<BoardDTO> getList(long curPage, String searchQuery, String category) {
        Map<String, Object> searchParams = new HashMap<>();

        long to = ITEM_PER_PAGE * (curPage - 1) + 1;
        long from = to + ITEM_PER_PAGE - 1;

        searchParams.put("to", to);
        searchParams.put("from", from);

        searchParams.put("searchQuery", searchQuery);
        searchParams.put("category", category);

        return boardDAO.getList(searchParams);
    }

    // 선택된 게시글의 내용 select
    public BoardDTO getById(long targetId) {
        boardDAO.increaseViewCount(targetId);
        return boardDAO.getById(targetId);
    }

    // 게시글 등록
    public void posting(BoardDTO dto) {
        boardDAO.posting(dto);
    }

    // 게시글 삭제
    public void deleteById(long targetId) {
        boardDAO.deleteById(targetId);
    }

    // 게시글 수정
    public void updateById(BoardDTO dto) {
        boardDAO.updateById(dto);
    }
}
