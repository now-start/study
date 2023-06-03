package org.nowstart.study.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.nowstart.study.data.dto.UserDto;
import org.nowstart.study.data.entity.UserEntity;
import org.nowstart.study.data.vo.request.UserRequestVo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDto toDto(UserRequestVo userRequestVo);

    UserEntity toEntity(UserDto userDto);
}
