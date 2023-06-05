package org.nowstart.study.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.data.dto.BoardDto;
import org.nowstart.study.data.mapper.Mapper;
import org.nowstart.study.data.vo.request.BoardRequestVo;
import org.nowstart.study.data.vo.response.CommResponseVo;
import org.nowstart.study.service.serviceimpl.BoardServiceImpl;
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
    private final BoardServiceImpl boardService;

    @GetMapping("/board/list")
    public CommResponseVo<BoardDto> findAllBoard() {
        log.info("[BoardController][findAllBoard][/board/list]");
        return boardService.findAllBoard();
    }

    @PostMapping("/board")
    public CommResponseVo<BoardDto> saveBoard(@RequestBody @Valid BoardRequestVo boardRequestVo) {
        log.info("[BoardController][saveBoard][/board] : {}", boardRequestVo.toString());
        boardService.saveBoard(mapper.toDto(boardRequestVo));
        return CommResponseVo.<BoardDto>builder().build();
    }

    @PutMapping("/board/{id}")
    public CommResponseVo<BoardDto> updateBoard(@PathVariable String id, @RequestBody @Valid BoardRequestVo boardRequestVo) {
        log.info("[BoardController][updateBoard][/board] : {}", boardRequestVo.toString());
        boardService.updateBoard(id, mapper.toDto(boardRequestVo));
        return CommResponseVo.<BoardDto>builder().build();
    }

    @DeleteMapping("/board/{id}")
    public CommResponseVo<BoardDto> deleteBoard(@PathVariable String id) {
        log.info("[BoardController][deleteBoard][/board] : {}", id);
        boardService.deleteBoard(id);
        return CommResponseVo.<BoardDto>builder().build();
    }
}
