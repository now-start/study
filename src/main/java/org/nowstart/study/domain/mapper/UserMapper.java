package org.nowstart.study.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.nowstart.study.domain.dto.UserDto;
import org.nowstart.study.domain.vo.request.UserRequestVo;
import org.nowstart.study.domain.vo.response.UserResponseVo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDto(UserRequestVo userRequestVo);

    UserResponseVo toVo(UserDto userVo);
}
