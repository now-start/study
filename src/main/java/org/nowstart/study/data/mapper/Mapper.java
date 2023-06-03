package org.nowstart.study.data.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.nowstart.study.data.dto.BoardDto;
import org.nowstart.study.data.dto.UserDto;
import org.nowstart.study.data.entity.BoardEntity;
import org.nowstart.study.data.entity.UserEntity;
import org.nowstart.study.data.vo.request.BoardRequestVo;
import org.nowstart.study.data.vo.request.UserRequestVo;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Mapper {

    BoardDto toDto(BoardEntity boardEntity);

    BoardDto toDto(BoardRequestVo boardRequestVo);

    UserDto toDto(UserRequestVo userRequestVo);

    BoardEntity toEntity(BoardDto boardDto);

    @Mapping(target = "password", source = "password")
    UserEntity toEntity(UserDto userDto, String password);
}
