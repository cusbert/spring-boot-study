package com.guestbook.guestbook.converter;

import com.guestbook.guestbook.dto.MovieDTO;
import com.guestbook.guestbook.dto.MovieImageDTO;
import com.guestbook.guestbook.entity.Movie;
import com.guestbook.guestbook.entity.MovieImage;
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
                MovieImage movieImage = MovieImage.builder()
                        .path(movieImageDTO.getPath())
                        .imgName(movieImageDTO.getImgName())
                        .uuid(movieImageDTO.getUuid())
                        .movie(movie)
                        .build();
                return movieImage;
            }).collect(Collectors.toList());
            entityMap.put("imgList", movieImages);
        }

        return entityMap;
    }
}
