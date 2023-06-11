package org.nowstart.study.data.dto.user;

import lombok.Builder;
import lombok.Data;
import org.nowstart.study.data.type.RolesType;

@Data
@Builder
public class UserSaveDto {

    private String id;
    private String password;
    private String name;
    private RolesType role;
}
