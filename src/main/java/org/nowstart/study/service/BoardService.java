package org.nowstart.study.service;

import org.nowstart.study.data.dto.board.BoardFindServiceDto;
import org.nowstart.study.data.dto.board.BoardServiceDto;
import org.nowstart.study.data.entity.UserEntity;
import org.nowstart.study.data.vo.response.BoardResponseVo;
import org.nowstart.study.data.vo.response.CommResponseVo;
import org.springframework.stereotype.Service;

@Service
public interface BoardService {

    CommResponseVo<BoardResponseVo> findAllBoard(BoardFindServiceDto boardServiceDto);

    void saveBoard(BoardServiceDto boardServiceDto);

    void updateBoard(String id, BoardServiceDto boardServiceDto);

    void deleteBoard(String id, UserEntity userEntity);
}
