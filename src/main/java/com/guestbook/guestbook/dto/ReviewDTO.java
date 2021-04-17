package com.guestbook.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long reviewId;
    private int grade;
    private String text;

    private Long  mno;

    private String memberId;
    private String email;
    private String name;

}
