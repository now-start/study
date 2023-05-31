package org.nowstart.study.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.data.dto.BoardDto;
import org.nowstart.study.data.mapper.BoardMapper;
import org.nowstart.study.data.vo.response.BoardResponseVo;
import org.nowstart.study.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;

    public BoardResponseVo findAllBoard() {
        List<BoardDto> resultSet = boardRepository.findAll().stream().map(boardMapper::toDto).toList();
        return BoardResponseVo.builder()
                .resultSet(resultSet)
                .build();
    }

    public void saveBoard(BoardDto boardDto) {
        boardRepository.save(boardMapper.toEntity(boardDto));
    }

    public void updateBoard(String id, BoardDto boardDto) {
        boardRepository.findById(id).orElseThrow().update(boardDto);
    }

    public void deleteBoard(String id) {
        boardRepository.deleteById(id);
    }
}
