package org.nowstart.study.domain.mapper;

import org.mapstruct.Mapper;
import org.nowstart.study.domain.dto.BoardDto;
import org.nowstart.study.domain.entity.BoardEntity;
import org.nowstart.study.domain.vo.request.BoardRequestVo;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    BoardDto toDto(BoardEntity boardEntity);

    BoardDto toDto(BoardRequestVo boardRequestVo);

    BoardEntity toEntity(BoardDto boardDto);
}
