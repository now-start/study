package org.nowstart.study.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDto {

    private String title;
    private String writer;
    private String contents;
}
