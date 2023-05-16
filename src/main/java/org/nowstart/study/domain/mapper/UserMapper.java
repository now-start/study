package org.nowstart.study.domain.mapper;

import org.mapstruct.Mapper;
import org.nowstart.study.domain.dto.UserDto;
import org.nowstart.study.domain.vo.request.UserVo;
import org.nowstart.study.domain.vo.response.UserResponseVo;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(UserVo userVo);

    UserResponseVo toVo(UserDto userVo);
}
