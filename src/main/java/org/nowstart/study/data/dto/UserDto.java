package org.nowstart.study.data.dto;

import lombok.Builder;
import lombok.Data;
import org.nowstart.study.data.type.RolesType;

@Data
@Builder
public class UserDto {

    private String id;
    private String password;
    private String name;
    private RolesType role;
}
