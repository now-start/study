package org.nowstart.study.domain.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CommResponseVo {

    @Builder.Default
    private String flag = "0000";

    @Builder.Default
    private String message = "성공";
}
