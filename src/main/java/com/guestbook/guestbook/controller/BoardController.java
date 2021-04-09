package com.guestbook.guestbook.controller;

import com.guestbook.guestbook.dto.BoardDTO;
import com.guestbook.guestbook.dto.PageRequestDTO;
import com.guestbook.guestbook.dto.PageResultDTO;
import com.guestbook.guestbook.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping("/board")
    public PageResultDTO<BoardDTO, Object[]> list(PageRequestDTO pageRequestDTO) {
        PageResultDTO<BoardDTO, Object[]> result = service.getList(pageRequestDTO);
        return result;
    }

    @GetMapping("/board2")
    public  Page<Object[]> list2(PageRequestDTO pageRequestDTO) {
        Page<Object[]> result = service.getList2(pageRequestDTO);
        return result;
    }

    @PostMapping("/board")
    public BoardDTO register(@RequestBody BoardDTO dto) {
        return service.register(dto);
    }

    @GetMapping("/board/{bno}")
    public BoardDTO get(@PathVariable(name = "bno") Long bno) {
        return service.get(bno);
    }

    @PutMapping("/board/{bno}")
    public void get(@PathVariable(name = "bno") Long bno,
                        @RequestBody BoardDTO dto) {
        service.modify(dto);
    }

    @DeleteMapping("/board/{bno}")
    public void delete(@PathVariable(name = "bno") Long bno) {
        service.removeWithReplies(bno);
    }


}
