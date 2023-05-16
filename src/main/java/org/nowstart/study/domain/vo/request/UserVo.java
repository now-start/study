package org.nowstart.study.domain.vo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class UserVo {
    @Size(min = 3)
    @NotBlank
    private String id;

    @NotBlank
    private String password;
}
