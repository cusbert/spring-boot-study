package com.guestbook.guestbook.converter;

import com.guestbook.guestbook.dto.ReviewDTO;
import com.guestbook.guestbook.entity.Member;
import com.guestbook.guestbook.entity.Movie;
import com.guestbook.guestbook.entity.Review;
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
