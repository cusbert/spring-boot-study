package com.guestbook.guestbook.service.impl;

import com.guestbook.guestbook.converter.ReviewConverter;
import com.guestbook.guestbook.dto.ReviewDTO;
import com.guestbook.guestbook.entity.Movie;
import com.guestbook.guestbook.entity.Review;
import com.guestbook.guestbook.repository.ReviewRepository;
import com.guestbook.guestbook.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getListOfMovie(Long mno) {

        Movie movie = Movie.builder().mno(mno).build();

        List<Review> result = reviewRepository.findByMovie(movie);
        return result.stream().map(review -> ReviewConverter.entityToDTO(review)).collect(Collectors.toList());
    }
}
