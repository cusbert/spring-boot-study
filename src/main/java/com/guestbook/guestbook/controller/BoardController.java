package com.guestbook.guestbook.controller;

import com.guestbook.guestbook.dto.BoardDTO;
import com.guestbook.guestbook.dto.PageRequestDTO;
import com.guestbook.guestbook.dto.PageResultDTO;
import com.guestbook.guestbook.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
