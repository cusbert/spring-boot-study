package com.guestbook.guestbook.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll() // permitAll 설정
                .antMatchers("/sample/member").hasRole("USER"); // USER 권한만 접근 가능

        http.formLogin(); // 인증 문제시 로그인 화면면
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 테스트용 사용자 1명 생성

        // 사용자 계정은 user1
        auth.inMemoryAuthentication().withUser("user1")
                .password("$2a$10$wbni5WOD2fKQ9UQirwkqRev2GQep.Ifdak9FdKL4aNrGgTe8Dbsma") // 1111 인코딩
                .roles("USER");
    }
}
