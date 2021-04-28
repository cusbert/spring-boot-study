package com.study.springboot.repository;

import com.study.springboot.entity.Member;
import com.study.springboot.entity.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMember() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .id("userID"+i)
                    .email("mail"+ i +"@mail.com")
                    .password(passwordEncoder.encode("1111"))
                    .name("agent " + i)
                    .fromSocial(false)
                    .build();

            // default role
            member.addMemberRole(MemberRole.USER);

            if(i > 80) {
                member.addMemberRole(MemberRole.MANAGER);
            }

            if(i > 90) {
                member.addMemberRole(MemberRole.ADMIN);
            }

            memberRepository.save(member);
        });
    }

    @Test
    public void testRead() {
        Optional<Member> result = memberRepository.findByEmail("mail85@mail.com", false);
        System.out.println(result);
        Member member = result.get();
        System.out.println(member);
    }
}
