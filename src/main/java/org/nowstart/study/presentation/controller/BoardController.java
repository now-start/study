package org.nowstart.study.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nowstart.study.domain.mapper.BoardMapper;
import org.nowstart.study.domain.vo.request.BoardRequestVo;
import org.nowstart.study.domain.vo.response.BoardResponseVo;
import org.nowstart.study.domain.vo.response.CommResponseVo;
import org.nowstart.study.service.BoardService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardMapper boardMapper;
    private final BoardService boardService;

    @GetMapping("/board/list")
    public BoardResponseVo findAllBoard() {
        log.info("[BoardController][findAllBoard][/board/list]");
        return boardService.findAllBoard();
    }

    @PostMapping("/board")
    public CommResponseVo saveBoard(@RequestBody @Valid BoardRequestVo boardRequestVo) {
        log.info("[BoardController][saveBoard][/board] : {}", boardRequestVo.toString());
        boardService.saveBoard(boardMapper.toDto(boardRequestVo));
        return CommResponseVo.builder().build();
    }

    @PutMapping("/board/{id}")
    public CommResponseVo updateBoard(@PathVariable String id, @RequestBody @Valid BoardRequestVo boardRequestVo) {
        log.info("[BoardController][updateBoard][/board] : {}", boardRequestVo.toString());
        boardService.updateBoard(id, boardMapper.toDto(boardRequestVo));
        return CommResponseVo.builder().build();
    }

    @DeleteMapping("/board/{id}")
    public CommResponseVo deleteBoard(@PathVariable String id) {
        log.info("[BoardController][deleteBoard][/board] : {}", id);
        boardService.deleteBoard(id);
        return CommResponseVo.builder().build();
    }
}
