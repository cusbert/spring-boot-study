package com.guestbook.guestbook.controller;

import com.guestbook.guestbook.dto.MovieDTO;
import com.guestbook.guestbook.dto.PageRequestDTO;
import com.guestbook.guestbook.dto.PageResultDTO;
import com.guestbook.guestbook.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @PostMapping("/movies")
    public ResponseEntity<String> register(@RequestBody MovieDTO movieDTO) {

        service.register(movieDTO);
        return new ResponseEntity<>("success",  HttpStatus.OK);
    }

    @GetMapping("/movies")
    public ResponseEntity< PageResultDTO<MovieDTO, Object[]>> list(PageRequestDTO pageRequestDTO) {

        PageResultDTO<MovieDTO, Object[]> list = service.getList(pageRequestDTO);
       return new ResponseEntity<>(list,  HttpStatus.OK);
    }
}
