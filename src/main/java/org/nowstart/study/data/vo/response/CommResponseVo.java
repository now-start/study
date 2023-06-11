package org.nowstart.study.data.vo.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommResponseVo<T> {

    @Builder.Default
    private String flag = "0000";

    @Builder.Default
    private String message = "성공";

    @Builder.Default
    private List<T> resultSet = null;
}
