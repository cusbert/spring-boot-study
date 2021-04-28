package com.study.springboot.converter;

import com.study.springboot.dto.ReviewDTO;
import com.study.springboot.entity.Member;
import com.study.springboot.entity.Movie;
import com.study.springboot.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

    public static ReviewDTO entityToDTO(Review review) {
        return ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .grade(review.getGrade())
                .text(review.getText())
                .mno(review.getMovie().getMno())
                .memberId(review.getMember().getId())
                .email(review.getMember().getEmail())
                .name(review.getMember().getName())
                .build();
    }

    public static Review dtoToEntity(ReviewDTO reviewDTO) {
        return Review.builder()
                .reviewId(reviewDTO.getReviewId())
                .grade(reviewDTO.getGrade())
                .text(reviewDTO.getText())
                .movie(Movie.builder()
                                .mno(reviewDTO.getMno())
                                .build())
                .member(Member.builder()
                        .id(reviewDTO.getMemberId())
                        .build())
                .build();
    }
}
