package org.nowstart.study.repository.impl;

import static org.nowstart.study.data.entity.QBoardEntity.boardEntity;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.nowstart.study.data.dto.board.BoardFindServiceDto;
import org.nowstart.study.data.entity.BoardEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardQueryDslRepositoryImpl implements BoardQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BoardEntity> findAll(BoardFindServiceDto boardFindServiceDto) {
        return jpaQueryFactory.selectFrom(boardEntity)
            .where(
                inWriter(boardFindServiceDto),
                inContent(boardFindServiceDto)
            ).fetch();
    }

    private BooleanExpression inWriter(BoardFindServiceDto boardFindServiceDto) {
        return boardFindServiceDto.getContent().isBlank() ? null : boardEntity.userEntity.id.in(boardFindServiceDto.getContent());
    }

    private Predicate inContent(BoardFindServiceDto boardFindServiceDto) {
        return boardFindServiceDto.getContent().isBlank() ? null : boardEntity.title.in(boardFindServiceDto.getContent()).or(boardEntity.contents.in(boardFindServiceDto.getContent()));
    }
}
