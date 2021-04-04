package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
