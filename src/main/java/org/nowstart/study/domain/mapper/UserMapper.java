package org.nowstart.study.domain.mapper;

import org.mapstruct.Mapper;
import org.nowstart.study.domain.dto.UserDto;
import org.nowstart.study.domain.vo.requestvo.UserVo;
import org.nowstart.study.domain.vo.responsevo.UserResponseVo;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(UserVo userVo);

    UserResponseVo toVo(UserDto userVo);
}
