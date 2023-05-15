package org.nowstart.study.domain.vo.requestvo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class UserVo {
    @NotBlank
    private String id;

    @NotBlank
    private String password;
}
