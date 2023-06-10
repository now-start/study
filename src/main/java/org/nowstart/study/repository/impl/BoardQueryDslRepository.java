package org.nowstart.study.repository.impl;

import java.util.List;
import org.nowstart.study.data.dto.BoardDto;
import org.nowstart.study.data.entity.BoardEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardQueryDslRepository {
    List<BoardEntity> findAll(BoardDto boardDto);
}
