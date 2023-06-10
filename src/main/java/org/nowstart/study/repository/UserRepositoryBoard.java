package org.nowstart.study.repository;

import org.nowstart.study.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryBoard extends JpaRepository<UserEntity, String> {
}
