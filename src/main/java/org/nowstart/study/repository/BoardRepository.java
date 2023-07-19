package org.nowstart.study.repository;

import org.nowstart.study.data.entity.BoardEntity;
import org.nowstart.study.repository.impl.BoardQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, String>, BoardQueryDslRepository {

}

