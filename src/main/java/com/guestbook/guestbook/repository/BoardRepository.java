package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // Object[]
    @Query("select w, b from Board b " +
            "left outer join b.writer w " +
            "where b.bno =:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    @Query("SELECT B, R FROM Board B " +
                  "LEFT JOIN Reply R " +
                  "ON R.board = B " +
                  "WHERE B.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    @Query(value = "SELECT b, w, count(r) " +
            "FROM Board b " +
            "LEFT JOIN b.writer w " +
            "LEFT JOIN Reply r " +
            "ON r.board = b " +
            "Group by b",
            countQuery = "select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);


    @Query("select b, w, count(r) from Board b " +
            "left outer join b.writer w " +
            "left outer join Reply r on r.board = b " +
            "where b.bno =:bno")
    Object getBoardByBno(@Param("bno") Long bno);
}
