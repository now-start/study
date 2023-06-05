package org.nowstart.study.data.dto;

import lombok.Builder;
import lombok.Data;
import org.nowstart.study.data.entity.UserEntity;

@Data
@Builder
public class BoardDto {

    private String title;
    private String contents;
    private UserEntity userEntity;
}
