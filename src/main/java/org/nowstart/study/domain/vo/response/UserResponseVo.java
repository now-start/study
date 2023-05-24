package org.nowstart.study.domain.vo.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@JsonRootName("result")
public class UserResponseVo extends CommResponseVo {
    String id;
    String password;
}
