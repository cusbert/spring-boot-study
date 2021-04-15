package com.guestbook.guestbook.service.impl;

import com.guestbook.guestbook.converter.MovieConverter;
import com.guestbook.guestbook.dto.MovieDTO;
import com.guestbook.guestbook.entity.Movie;
import com.guestbook.guestbook.entity.MovieImage;
import com.guestbook.guestbook.repository.MovieImageRepository;
import com.guestbook.guestbook.repository.MovieRepository;
import com.guestbook.guestbook.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieImageRepository imageRepository;


    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {

        Map<String, Object> entityMap = MovieConverter.dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);
        movieImageList.forEach(movieImage -> {
            imageRepository.save(movieImage);
        });

        return movie.getMno();
    }
}
