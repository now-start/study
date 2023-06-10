package org.nowstart.study.service.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.data.dto.BoardDto;
import org.nowstart.study.data.entity.BoardEntity;
import org.nowstart.study.data.entity.UserEntity;
import org.nowstart.study.data.mapper.Mapper;
import org.nowstart.study.data.type.RolesType;
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

    public CommResponseVo<BoardDto> findAllBoard(BoardDto boardDto) {
        return CommResponseVo.<BoardDto>builder()
            .resultSet(boardRepository.findAll(boardDto).stream().map(mapper::toDto).toList())
            .build();
    }

    public void saveBoard(BoardDto boardDto) {
        boardRepository.save(mapper.toEntity(boardDto));
    }

    public void updateBoard(String id, BoardDto boardDto) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow();
        if (!Objects.requireNonNull(boardEntity.getUserEntity().getId()).equals(boardDto.getUserEntity().getId())) {
            throw new SecurityException();
        }
        boardEntity.update(boardDto);
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
