package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
