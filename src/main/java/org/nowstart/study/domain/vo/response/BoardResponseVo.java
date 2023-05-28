package org.nowstart.study.domain.vo.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.nowstart.study.domain.dto.BoardDto;

@Getter
@SuperBuilder
public class BoardResponseVo extends CommResponseVo {

    private List<BoardDto> resultSet;
}
