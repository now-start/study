package org.nowstart.study.data.vo.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UserResponseVo extends CommResponseVo {

    String id;
    String password;
}
