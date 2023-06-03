package org.nowstart.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.data.dto.BoardDto;
import org.nowstart.study.data.mapper.Mapper;
import org.nowstart.study.data.vo.response.CommResponseVo;
import org.nowstart.study.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final Mapper mapper;
    private final BoardRepository boardRepository;

    public CommResponseVo<BoardDto> findAllBoard() {
        return CommResponseVo.<BoardDto>builder()
            .resultSet(boardRepository.findAll().stream().map(mapper::toDto).toList())
            .build();
    }

    public void saveBoard(BoardDto boardDto) {
        boardRepository.save(mapper.toEntity(boardDto));
    }

    public void updateBoard(String id, BoardDto boardDto) {
        boardRepository.findById(id).orElseThrow().update(boardDto);
    }

    public void deleteBoard(String id) {
        boardRepository.deleteById(id);
    }
}
