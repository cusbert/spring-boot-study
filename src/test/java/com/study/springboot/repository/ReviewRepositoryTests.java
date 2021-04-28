package com.study.springboot.repository;

import com.study.springboot.entity.Member;
import com.study.springboot.entity.Movie;
import com.study.springboot.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.List;
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


    @Test
    public void testGetMoveReview() {
        Movie movie = Movie.builder().mno(90l).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview -> {
                    System.out.println(movieReview.toString());
                    System.out.println(movieReview.getMember().getEmail());
                }

        );
    }


    @Commit
    @Transactional
    @Test
    public void testDeleteMember() {

        Member member = Member.builder().id("userID1").build();

        reviewRepository.deleteByMember(member);
        memberRepository.delete(member);

    }

}
