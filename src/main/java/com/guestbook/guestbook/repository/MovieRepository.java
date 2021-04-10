package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
