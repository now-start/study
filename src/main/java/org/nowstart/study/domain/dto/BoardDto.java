package org.nowstart.study.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDto {

    String id;
    String title;
    String writer;
    String contents;
}
