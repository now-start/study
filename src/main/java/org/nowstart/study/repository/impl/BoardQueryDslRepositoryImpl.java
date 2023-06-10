package org.nowstart.study.repository.impl;

import static org.nowstart.study.data.entity.QBoardEntity.boardEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.nowstart.study.data.dto.BoardDto;
import org.nowstart.study.data.entity.BoardEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardQueryDslRepositoryImpl implements BoardQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BoardEntity> findAll(BoardDto boardDto) {
        return jpaQueryFactory
            .selectFrom(boardEntity)
            .where()
            .fetch();
    }
}
