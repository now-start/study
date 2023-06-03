package org.nowstart.study.data.dto;

import lombok.Builder;
import lombok.Data;
import org.nowstart.study.data.type.RolesType;

@Data
@Builder
public class UserDto {

    String id;
    String password;
    String name;
    RolesType role;
}
