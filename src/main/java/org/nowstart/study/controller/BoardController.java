package org.nowstart.study.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.data.dto.board.BoardServiceDto;
import org.nowstart.study.data.entity.UserEntity;
import org.nowstart.study.data.mapper.Mapper;
import org.nowstart.study.data.vo.request.BoardFindRequestVo;
import org.nowstart.study.data.vo.request.BoardRequestVo;
import org.nowstart.study.data.vo.response.BoardResponseVo;
import org.nowstart.study.data.vo.response.CommResponseVo;
import org.nowstart.study.service.BoardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final Mapper mapper;
    private final BoardService boardService;

    @GetMapping("/board/list")
    public CommResponseVo<BoardResponseVo> findAllBoard(@Valid BoardFindRequestVo boardFindRequestVo) {
        log.info("[BoardController][findAllBoard][/board/list]");
        return boardService.findAllBoard(mapper.toServiceDto(boardFindRequestVo));
    }

    @PostMapping("/board")
    public CommResponseVo<BoardServiceDto> saveBoard(@RequestBody @Valid BoardRequestVo boardRequestVo, @AuthenticationPrincipal UserEntity userEntity) {
        log.info("[BoardController][saveBoard][/board] : {}", boardRequestVo.toString());
        boardService.saveBoard(mapper.toServiceDto(boardRequestVo, userEntity));
        return CommResponseVo.<BoardServiceDto>builder().build();
    }

    @PutMapping("/board/{id}")
    public CommResponseVo<BoardServiceDto> updateBoard(@PathVariable String id, @RequestBody @Valid BoardRequestVo boardRequestVo, @AuthenticationPrincipal UserEntity userEntity) {
        log.info("[BoardController][updateBoard][/board] : {}", boardRequestVo.toString());
        boardService.updateBoard(id, mapper.toServiceDto(boardRequestVo, userEntity));
        return CommResponseVo.<BoardServiceDto>builder().build();
    }

    @DeleteMapping("/board/{id}")
    public CommResponseVo<BoardServiceDto> deleteBoard(@PathVariable String id, @AuthenticationPrincipal UserEntity userEntity) {
        log.info("[BoardController][deleteBoard][/board] : {}", id);
        boardService.deleteBoard(id, userEntity);
        return CommResponseVo.<BoardServiceDto>builder().build();
    }
}
