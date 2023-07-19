package org.nowstart.study.repository.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nowstart.study.config.TestQuerydslConfig;
import org.nowstart.study.data.dto.board.BoardFindServiceDto;
import org.nowstart.study.data.entity.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({TestQuerydslConfig.class})
@ExtendWith(SpringExtension.class)
class BoardQueryDslRepositoryImplTest {

    @Autowired
    BoardQueryDslRepositoryImpl boardQueryDslRepository;

    @Test
    void findAll() {
        //given
        BoardFindServiceDto boardFindServiceDto = BoardFindServiceDto.builder()
                .build();

        //when
        List<BoardEntity> result = boardQueryDslRepository.findAll(boardFindServiceDto);
        System.out.println(result);

        //then
        assertThat(result).isEmpty();
    }
}