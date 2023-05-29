package org.nowstart.study.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.nowstart.study.domain.dto.BoardDto;
import org.nowstart.study.domain.entity.BoardEntity;
import org.nowstart.study.domain.mapper.BoardMapper;
import org.nowstart.study.domain.vo.response.BoardResponseVo;
import org.nowstart.study.repository.BoardRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class BoardServiceTest {

    @InjectMocks
    BoardService service;
    @Mock
    BoardRepository repository;
    @Spy
    BoardMapper mapper;

    @Test
    void findAllBoard() {
        //given

        //when
        BoardResponseVo result = service.findAllBoard();

        //then
        assertThat(result.getFlag()).isEqualTo("0000");
        assertThat(result.getMessage()).isEqualTo("성공");
    }

    @Test
    void saveBoard() {
        //given
        BoardDto boardDto = BoardDto.builder()
            .title("testTitle")
            .writer("testWriter")
            .contents("testContents")
            .build();

        //when

        //then
        assertDoesNotThrow(() -> service.saveBoard(boardDto));
    }

    @Test
    void updateBoard() {
        //given
        String id = "testId";
        BoardDto boardDto = BoardDto.builder()
                .title("testTitle")
                .writer("testWriter")
                .contents("testContents")
                .build();

        //when
        given(repository.findById(any())).willReturn(Optional.of(BoardEntity.builder()
                .build()));

        //then
        assertDoesNotThrow(() -> service.updateBoard(id, boardDto));
    }

    @Test
    void updateBoard_exception() {
        //given
        String id = "testId";
        BoardDto boardDto = BoardDto.builder()
                .title("testTitle")
                .writer("testWriter")
                .contents("testContents")
                .build();

        //when
        given(repository.findById(any())).willThrow(NoSuchElementException.class);

        //then
        assertThatThrownBy(() -> service.updateBoard(id, boardDto)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void deleteBoard() {
        //given
        String id = "testid";

        //when

        //then
        assertDoesNotThrow(() -> service.deleteBoard(id));
    }
}