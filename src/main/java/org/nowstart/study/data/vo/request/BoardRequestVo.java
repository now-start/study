package org.nowstart.study.data.vo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BoardRequestVo {

    @NotBlank
    @Size(max = 10)
    private String title;

    private String contents;
}
