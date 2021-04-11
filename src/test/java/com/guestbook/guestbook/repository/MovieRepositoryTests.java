package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Movie;
import com.guestbook.guestbook.entity.MovieImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Commit
    @Transactional
    @Test
    public void testInsertMovies() {

        IntStream.rangeClosed(1, 20).forEach(i -> {
            Movie movie = Movie.builder()
                    .title("Movie title" + i).build();

            System.out.println(movie.toString());

            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg")
                        .build();
                movieImageRepository.save(movieImage);
            }

            System.out.println("==========end");
        });
    }

    @Test
    public void testListPage() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));
        Page<Object[]> result = movieRepository.getListPage(pageable);

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }
    }
}
