package org.nowstart.study.data.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardResponseVo {

    private String title;
    private String contents;
    private String username;
}
