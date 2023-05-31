package org.nowstart.study.data.mapper;

import org.mapstruct.Mapper;
import org.nowstart.study.data.dto.BoardDto;
import org.nowstart.study.data.entity.BoardEntity;
import org.nowstart.study.data.vo.request.BoardRequestVo;

@Mapper(componentModel = "spring")
public interface BoardMapper {

    BoardDto toDto(BoardEntity boardEntity);

    BoardDto toDto(BoardRequestVo boardRequestVo);

    BoardEntity toEntity(BoardDto boardDto);
}
