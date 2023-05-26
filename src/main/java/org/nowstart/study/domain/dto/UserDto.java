package org.nowstart.study.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    String id;
    String password;
}
