package org.nowstart.study.data.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.nowstart.study.data.dto.board.BoardFindServiceDto;
import org.nowstart.study.data.dto.board.BoardServiceDto;
import org.nowstart.study.data.dto.user.UserSaveDto;
import org.nowstart.study.data.entity.BoardEntity;
import org.nowstart.study.data.entity.UserEntity;
import org.nowstart.study.data.vo.request.BoardFindRequestVo;
import org.nowstart.study.data.vo.request.BoardRequestVo;
import org.nowstart.study.data.vo.request.UserRequestVo;
import org.nowstart.study.data.vo.response.BoardResponseVo;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Mapper {

    BoardServiceDto toServiceDto(BoardRequestVo boardRequestVo, UserEntity userEntity);

    UserSaveDto toServiceDto(UserRequestVo userRequestVo);

    BoardFindServiceDto toServiceDto(BoardFindRequestVo boardFindRequestVo);

    BoardEntity toEntity(BoardServiceDto boardServiceDto);

    @Mapping(target = "password", source = "password")
    UserEntity toEntity(UserSaveDto userSaveDto, String password);

    BoardResponseVo toResponseVo(BoardEntity boardEntity);
}
