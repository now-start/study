package org.nowstart.study.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nowstart.study.domain.dto.BoardDto;

@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends CommEntity {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String title;

    @Column
    String writer;

    @Column
    String contents;

    @ManyToOne
    UserEntity userEntity;

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
}
