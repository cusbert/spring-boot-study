package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
