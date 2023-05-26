package org.nowstart.study.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nowstart.study.domain.dto.BoardDto;

@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity {

    @Id
    @GeneratedValue
    Integer id;

    @Column
    String title;

    @Column
    String writer;

    @Column
    String contents;

    @Column(updatable = false)
    LocalDateTime registrationDate;

    @Column
    LocalDateTime modifyDate;

    @Builder
    public BoardEntity(String title, String writer, String contents) {
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }

    public void update(BoardDto boardDto) {
        this.title = boardDto.getTitle();
        this.writer = boardDto.getWriter();
        this.contents = boardDto.getContents();
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
