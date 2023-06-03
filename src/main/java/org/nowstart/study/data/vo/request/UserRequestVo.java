package org.nowstart.study.data.vo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.nowstart.study.data.anotation.ValidEnum;
import org.nowstart.study.data.type.RolesType;

@Getter
@ToString
@AllArgsConstructor
public class UserRequestVo {
    @Size(min = 3)
    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    @ValidEnum(enumClass = RolesType.class)
    private String role;
}
