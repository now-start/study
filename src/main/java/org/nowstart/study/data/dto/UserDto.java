package org.nowstart.study.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    String id;
    String password;
    String name;
}
