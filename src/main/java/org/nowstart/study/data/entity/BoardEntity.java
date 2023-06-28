package org.nowstart.study.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nowstart.study.data.dto.board.BoardServiceDto;

@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @ManyToOne
    private UserEntity userEntity;

    @Builder
    public BoardEntity(String title, String contents, UserEntity userEntity) {
        this.title = title;
        this.contents = contents;
        this.userEntity = userEntity;
    }

    public void update(BoardServiceDto boardServiceDto) {
        this.title = boardServiceDto.getTitle();
        this.contents = boardServiceDto.getContents();
    }
}
