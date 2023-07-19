package org.nowstart.study.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.nowstart.study.data.dto.board.BoardFindServiceDto;
import org.nowstart.study.data.entity.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class BoardQueryDslRepositoryImplTest {

    @InjectMocks
    BoardQueryDslRepositoryImpl boardQueryDslRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void findAll() {
        //given
        BoardFindServiceDto boardFindServiceDto = BoardFindServiceDto.builder()
                .build();

        //when
        List<BoardEntity> result = boardQueryDslRepository.findAll(boardFindServiceDto);

        //then

    }
}