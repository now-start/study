package org.nowstart.study.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
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
import org.nowstart.study.service.impl.BoardServiceImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class BoardServiceTest {

    @InjectMocks
    BoardServiceImpl service;
    @Mock
    BoardRepositoryBoard repository;
    @Spy
    Mapper mapper;

    @Test
    void findAllBoard() {
        //given

        //when
        CommResponseVo<BoardResponseVo> result = service.findAllBoard(BoardFindServiceDto.builder().build());

        //then
        assertThat(result.getFlag()).isEqualTo("0000");
        assertThat(result.getMessage()).isEqualTo("성공");
    }

    @Test
    void saveBoard() {
        //given
        BoardServiceDto boardServiceDto = BoardServiceDto.builder()
            .title("testTitle")
            .contents("testContents")
            .build();

        //when

        //then
        assertDoesNotThrow(() -> service.saveBoard(boardServiceDto));
    }

    @Test
    void updateBoard() {
        //given
        String id = "testId";
        BoardServiceDto boardServiceDto = BoardServiceDto.builder()
            .title("testTitle")
            .contents("testContents")
            .userEntity(UserEntity.builder()
                .id(id)
                .build())
            .build();

        //when
        given(repository.findById(any())).willReturn(Optional.of(BoardEntity.builder()
            .userEntity(UserEntity.builder()
                .id(id)
                .build())
            .build()));

        //then
        assertDoesNotThrow(() -> service.updateBoard(id, boardServiceDto));
    }

    @Test
    void updateBoard_exception() {
        //given
        String id = "testId";
        BoardServiceDto boardServiceDto = BoardServiceDto.builder()
            .title("testTitle")
            .contents("testContents")
            .build();

        //when
        given(repository.findById(any())).willThrow(NoSuchElementException.class);

        //then
        assertThatThrownBy(() -> service.updateBoard(id, boardServiceDto)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void updateBoard_securityException() {
        //given
        String id = "testId";
        BoardServiceDto boardServiceDto = BoardServiceDto.builder()
            .title("testTitle")
            .contents("testContents")
            .userEntity(UserEntity.builder()
                .id(id)
                .build())
            .build();

        //when
        given(repository.findById(any())).willReturn(Optional.of(BoardEntity.builder()
            .userEntity(UserEntity.builder()
                .id("differentId")
                .build())
            .build()));

        //then
        assertThatThrownBy(() -> service.updateBoard(id, boardServiceDto)).isInstanceOf(SecurityException.class);
    }

    @Test
    void deleteBoard() {
        //given
        String id = "testid";

        //when
        given(repository.findById(any())).willReturn(Optional.of(BoardEntity.builder()
            .userEntity(UserEntity.builder()
                .id(id)
                .build())
            .build()));

        //then
        assertDoesNotThrow(() -> service.deleteBoard(id, UserEntity.builder()
            .id(id)
            .role(RolesType.USER)
            .build()));
    }

    @Test
    void deleteBoard_differentId() {
        //given
        String id = "testid";

        //when
        given(repository.findById(any())).willReturn(Optional.of(BoardEntity.builder()
            .userEntity(UserEntity.builder()
                .id("differentId")
                .build())
            .build()));

        //then
        assertDoesNotThrow(() -> service.deleteBoard(id, UserEntity.builder()
            .role(RolesType.ADMIN)
            .build()));
    }

    @Test
    void deleteBoard_exception() {
        //given
        String id = "testid";

        //when
        given(repository.findById(any())).willThrow(NoSuchElementException.class);

        //then
        assertThatThrownBy(() -> service.deleteBoard(id, UserEntity.builder().build())).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void deleteBoard_securityException_differentId() {
        //given
        String id = "testid";

        //when
        given(repository.findById(any())).willReturn(Optional.of(BoardEntity.builder()
            .userEntity(UserEntity.builder()
                .id("differentId")
                .build())
            .build()));

        //then
        assertThatThrownBy(() -> service.deleteBoard(id, UserEntity.builder()
            .role(RolesType.USER)
            .build())).isInstanceOf(SecurityException.class);
    }
}