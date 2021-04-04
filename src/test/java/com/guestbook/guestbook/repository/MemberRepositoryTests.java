package com.guestbook.guestbook.repository;

import com.guestbook.guestbook.entity.Guestbook;
import com.guestbook.guestbook.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMember() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Member member = Member.builder()
                    .id("userID"+i)
                    .email("mail"+ i +"@mail.com")
                    .password("pw"+i)
                    .name("agent " + i)
                    .build();
            memberRepository.save(member);
        });
    }
}
