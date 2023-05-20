package org.nowstart.study.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    String id;

    @Column
    String password;

    @Column
    String name;

    @Column
    String roles;

    @Column(updatable = false)
    LocalDateTime registrationDate;

    @Column
    LocalDateTime modifyDate;

    @Builder
    public UserEntity(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    @PrePersist
    public void onPrePersist() {
        this.registrationDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.modifyDate = LocalDateTime.now();
    }
}
