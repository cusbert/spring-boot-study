package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
