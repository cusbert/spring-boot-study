package com.study.springboot.security.service;

import com.study.springboot.entity.Member;
import com.study.springboot.repository.MemberRepository;
import com.study.springboot.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 스프링에서 자동으로 빈 처리하여  로그인 사용자 정보 가져옴
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 로그인 유저 로깅 처리
        log.info("loadUserByUsername : username :" + username);

        Optional<Member> result = memberRepository.findByEmail(username, false);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("check email or social");
        }

        Member member = result.get();

        log.info("member : " + member);

        // MemberRole 은 스프잉 시큐리티에서 사용하는 SimpleGrantedAuthority 로 변환
        // "ROLE_" 접두어 추가해서 사용
        AuthMemberDTO authMemberDTO = new AuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                member.isFromSocial(),
                member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet())
        );

        authMemberDTO.setName(member.getName());
        authMemberDTO.setFromSocial(member.isFromSocial());



        return authMemberDTO;
    }
}
