package com.guestbook.guestbook.controller;

import com.guestbook.guestbook.dto.ReviewDTO;
import com.guestbook.guestbook.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/movies/{mno}/reviews/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno) {
        List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);
        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/movies/reviews")
    public ResponseEntity<ReviewDTO> addReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewDTO review = reviewService.register(reviewDTO);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/movies/{mno}/reviews/{reviewId}")
    public ResponseEntity<Long> modifyReview(@PathVariable("reviewId") Long reviewId,
                                             @RequestBody ReviewDTO reviewDTO) {
        reviewService.modify(reviewDTO);
        return new ResponseEntity<>(reviewId, HttpStatus.OK);
    }

    @DeleteMapping("/movies/{mno}/reviews/{reviewId}")
    public ResponseEntity<Long> removeReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.remove(reviewId);
        return new ResponseEntity<>(reviewId, HttpStatus.OK);
    }
}
