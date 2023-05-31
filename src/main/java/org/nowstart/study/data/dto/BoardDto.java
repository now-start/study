package org.nowstart.study.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDto {

    String title;
    String writer;
    String contents;
}
