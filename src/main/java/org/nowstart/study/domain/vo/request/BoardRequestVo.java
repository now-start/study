package org.nowstart.study.domain.vo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BoardRequestVo {

    private String id;

    @NotBlank
    @Size(max = 10)
    private String title;

    @NotBlank
    @Size(max = 10)
    private String writer;

    private String contents;
}
