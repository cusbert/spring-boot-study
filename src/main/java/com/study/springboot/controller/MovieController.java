package com.study.springboot.controller;

import com.study.springboot.dto.MovieDTO;
import com.study.springboot.dto.PageRequestDTO;
import com.study.springboot.dto.PageResultDTO;
import com.study.springboot.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/movies/{mno}")
    public ResponseEntity<MovieDTO> list(@PathVariable("mno") String mno) {

        MovieDTO movie = service.getMovie(Long.parseLong(mno));
        return new ResponseEntity<>(movie,  HttpStatus.OK);
    }
}
