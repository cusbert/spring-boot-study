package com.guestbook.guestbook.converter;

import com.guestbook.guestbook.dto.ReviewDTO;
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
}
