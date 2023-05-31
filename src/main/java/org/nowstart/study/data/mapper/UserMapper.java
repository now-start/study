package org.nowstart.study.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.nowstart.study.data.dto.UserDto;
import org.nowstart.study.data.entity.UserEntity;
import org.nowstart.study.data.vo.request.UserRequestVo;
import org.nowstart.study.data.vo.response.UserResponseVo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDto(UserRequestVo userRequestVo);

    UserResponseVo toVo(UserDto userVo);

    UserEntity toEntity(UserDto userDto);
}
