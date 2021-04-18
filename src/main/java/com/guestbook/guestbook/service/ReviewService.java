package com.guestbook.guestbook.service;

import com.guestbook.guestbook.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getListOfMovie(Long mno);

    ReviewDTO register(ReviewDTO reviewDTO);

    void modify(ReviewDTO reviewDTO);

    void remove(Long reviewId);
}
