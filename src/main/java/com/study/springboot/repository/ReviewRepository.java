package com.study.springboot.repository;

import com.study.springboot.entity.Member;
import com.study.springboot.entity.Movie;
import com.study.springboot.entity.Review;
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
