package com.study.springboot.service;

import com.study.springboot.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getListOfMovie(Long mno);

    ReviewDTO register(ReviewDTO reviewDTO);

    void modify(ReviewDTO reviewDTO);

    void remove(Long reviewId);
}
