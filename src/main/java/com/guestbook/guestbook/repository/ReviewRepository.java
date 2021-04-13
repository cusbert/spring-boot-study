package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Member;
import com.guestbook.guestbook.entity.Movie;
import com.guestbook.guestbook.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    @Modifying // update 나 delete 사용을 위해
    @Query("delete from Review mr where mr.member = :member")
    void deleteByMember(Member member);
}
