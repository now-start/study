package org.nowstart.study.repository;

import org.nowstart.study.data.entity.BoardEntity;
import org.nowstart.study.repository.impl.BoardQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepositoryBoard extends JpaRepository<BoardEntity, String>, BoardQueryDslRepository {

}

