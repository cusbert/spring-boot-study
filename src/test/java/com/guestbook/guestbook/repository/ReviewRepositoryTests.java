package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Member;
import com.guestbook.guestbook.entity.Movie;
import com.guestbook.guestbook.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void insertMovieReviews() {
        IntStream.rangeClosed(1, 200).forEach(i -> {

            Long mno = (long) (Math.random() * 100) + 1;
            String mid = "userID" + (int) ((Math.random() * 10) + 1);

            Member member = Member.builder().id(mid)
                    .build();

            Movie movie = Movie.builder()
                    .mno(mno)
                    .build();


            int grade = (int) (Math.random() * 5) + 1;
            String text = "this movie is good";

            if (grade <= 2) {
                text = "this movie is bad ";
            } else if (grade > 2 && grade <= 4) {
                text = "this movie is soso ";
            } else {
                text = "this movie is amazing ";
            }

            Review review = Review.builder()
                    .member(member)
                    .movie(movie)
                    .grade(grade)
                    .text(text + i)
                    .build();

            reviewRepository.save(review);

        });
    }


}
