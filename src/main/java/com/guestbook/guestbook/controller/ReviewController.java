package com.guestbook.guestbook.controller;

import com.guestbook.guestbook.dto.ReviewDTO;
import com.guestbook.guestbook.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews/{mno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno) {
        List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);
        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }
}
