package org.nowstart.study.data.vo.response;

import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.nowstart.study.data.dto.BoardDto;

@Getter
@SuperBuilder
public class BoardResponseVo extends CommResponseVo {

    private List<BoardDto> resultSet;
}
