package org.nowstart.study.data.vo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class BoardRequestVo {

    @NotBlank
    @Size(min = 1, max = 50)
    private String title;

    private String contents;
}
