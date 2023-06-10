package org.nowstart.study.data.vo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BoardFindRequestVo {

    private String searchType;
    private String contents;
}
