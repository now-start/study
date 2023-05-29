package org.nowstart.study.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.util.List;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends CommEntity implements Persistable<String> {

    @Id
    String id;

    @Column
    String password;

    @Column
    String name;

    @Column
    String roles;

    @OneToMany(mappedBy = "userEntity")
    List<BoardEntity> boardEntities;

    @Builder
    public UserEntity(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
