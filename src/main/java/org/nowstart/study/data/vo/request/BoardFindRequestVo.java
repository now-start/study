package org.nowstart.study.data.vo.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.nowstart.study.data.anotation.ValidEnum;
import org.nowstart.study.data.type.SearchType;

@Getter
@ToString
@Builder
public class BoardFindRequestVo {

    @ValidEnum(enumClass = SearchType.class)
    private String searchType;

    private String contents;
}
