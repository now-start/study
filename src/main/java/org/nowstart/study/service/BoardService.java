package org.nowstart.study.service;

import org.nowstart.study.data.dto.BoardDto;
import org.nowstart.study.data.vo.response.CommResponseVo;
import org.springframework.stereotype.Service;

@Service
public interface BoardService {

    CommResponseVo<BoardDto> findAllBoard();

    void saveBoard(BoardDto boardDto);

    void updateBoard(String id, BoardDto boardDto);

    void deleteBoard(String id);
}
