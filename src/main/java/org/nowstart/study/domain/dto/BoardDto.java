package org.nowstart.study.domain.dto;

import lombok.Data;

@Data
public class BoardDto {

    String id;
    String title;
    String writer;
    String contents;
}
