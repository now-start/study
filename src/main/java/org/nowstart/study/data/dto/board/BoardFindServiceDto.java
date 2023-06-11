package org.nowstart.study.data.dto.board;

import lombok.Builder;
import lombok.Data;
import org.nowstart.study.data.type.SearchType;

@Data
@Builder
public class BoardFindServiceDto {

    @Builder.Default
    private SearchType searchType = SearchType.UNDEFINED;
    @Builder.Default
    private String content = "";
}
