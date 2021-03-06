package com.study.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long mno;
    private String title;
    private double avg;
    private int reviewCount;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @Builder.Default
    private List<MovieImageDTO> imageDTOList = new ArrayList<>();
}
