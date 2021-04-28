package com.study.springboot.controller;

import com.study.springboot.dto.GuestbookDTO;
import com.study.springboot.dto.PageRequestDTO;
import com.study.springboot.dto.PageResultDTO;
import com.study.springboot.entity.Guestbook;
import com.study.springboot.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService service;

    @GetMapping("/guestbooks")
    public PageResultDTO<GuestbookDTO, Guestbook> list(PageRequestDTO pageRequestDTO) {
        PageResultDTO<GuestbookDTO, Guestbook> guestBooks = service.getList(pageRequestDTO);
        return guestBooks;
    }

    @PostMapping("/guestbooks")
    public GuestbookDTO regist(@RequestBody GuestbookDTO guestbookDTO){
        return service.register(guestbookDTO);
    }

    @GetMapping("/guestbooks/{gno}")
    public GuestbookDTO get( @PathVariable(name = "gno") Long gno) {
        GuestbookDTO guestbookDTO = service.read(gno);
        return service.read(gno);
    }

    @PutMapping("/guestbooks/{gno}")
    public GuestbookDTO modify(@RequestBody GuestbookDTO guestbookDTO){
       return service.modify(guestbookDTO);
    }

    @DeleteMapping("/guestbooks/{gno}")
    void deleteEmployee(@PathVariable Long gno) {
        service.delete(gno);
    }
}
