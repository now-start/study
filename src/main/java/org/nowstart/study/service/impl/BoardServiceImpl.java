package org.nowstart.study.service.impl;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.data.dto.board.BoardFindServiceDto;
import org.nowstart.study.data.dto.board.BoardServiceDto;
import org.nowstart.study.data.entity.BoardEntity;
import org.nowstart.study.data.entity.UserEntity;
import org.nowstart.study.data.mapper.Mapper;
import org.nowstart.study.data.type.RolesType;
import org.nowstart.study.data.vo.response.BoardResponseVo;
import org.nowstart.study.data.vo.response.CommResponseVo;
import org.nowstart.study.exception.SecurityException;
import org.nowstart.study.repository.BoardRepositoryBoard;
import org.nowstart.study.service.BoardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final Mapper mapper;
    private final BoardRepositoryBoard boardRepository;

    public CommResponseVo<BoardResponseVo> findAllBoard(BoardFindServiceDto boardFindServiceDto) {
        List<BoardResponseVo> resultSet = boardRepository.findAll(boardFindServiceDto).stream().map(mapper::toResponseVo).toList();
        return CommResponseVo.<BoardResponseVo>builder()
            .resultSet(resultSet)
            .build();
    }

    public void saveBoard(BoardServiceDto boardServiceDto) {
        boardRepository.save(mapper.toEntity(boardServiceDto));
    }

    public void updateBoard(String id, BoardServiceDto boardServiceDto) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow();
        if (!Objects.requireNonNull(boardEntity.getUserEntity().getId()).equals(boardServiceDto.getUserEntity().getId())) {
            throw new SecurityException();
        }
        boardEntity.update(boardServiceDto);
    }

    public void deleteBoard(String id, UserEntity userEntity) {
        boardRepository.findById(id).ifPresent(boardEntity -> {
            if (Objects.equals(userEntity.getRole().getRole(), RolesType.ADMIN.getRole()) || Objects.requireNonNull(boardEntity.getUserEntity().getId()).equals(userEntity.getId())) {
                boardRepository.deleteById(id);
            } else {
                throw new SecurityException();
            }
        });
    }
}
