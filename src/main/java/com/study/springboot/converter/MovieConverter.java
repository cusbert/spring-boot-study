package com.study.springboot.converter;

import com.study.springboot.dto.MovieDTO;
import com.study.springboot.dto.MovieImageDTO;
import com.study.springboot.entity.Movie;
import com.study.springboot.entity.MovieImage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MovieConverter {

    public static Map<String, Object> dtoToEntity(MovieDTO dto) {

        Map<String, Object> entityMap = new HashMap<>();
        Movie movie = Movie.builder()
                .mno(dto.getMno())
                .title(dto.getTitle())
                .build();

        entityMap.put("movie", movie);
        List<MovieImageDTO> imageDTOList = dto.getImageDTOList();

        // movieImageDTO 처리
        if (imageDTOList !=null && imageDTOList.size() > 0) {
            List<MovieImage> movieImages = imageDTOList.stream().map(movieImageDTO -> {
                return MovieImage.builder()
                        .path(movieImageDTO.getPath())
                        .imgName(movieImageDTO.getImgName())
                        .uuid(movieImageDTO.getUuid())
                        .movie(movie)
                        .build();
            }).collect(Collectors.toList());
            entityMap.put("imgList", movieImages);
        }

        return entityMap;
    }

    public static MovieDTO entityToDTO(Movie movie, List<MovieImage> movieImages, Double avg, Long reviewCount) {

        MovieDTO movieDTO = MovieDTO.builder()
                .mno(movie.getMno())
                .title(movie.getTitle())
                .regDate(movie.getRegDate())
                .modDate(movie.getModDate())
                .build();

        List<MovieImageDTO> movieImageDTOs = movieImages.stream().map(movieImage -> {
            return MovieImageDTO.builder()
                    .imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .uuid(movieImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        movieDTO.setImageDTOList(movieImageDTOs);
        movieDTO.setAvg(avg);
        movieDTO.setReviewCount(reviewCount.intValue());

        return movieDTO;
    }

}
