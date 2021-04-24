package com.guestbook.guestbook.config;

import com.guestbook.guestbook.security.filter.ApiCheckFilter;
import com.guestbook.guestbook.security.filter.ApiLoginFilter;
import com.guestbook.guestbook.security.handler.ApiLoginFailHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

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

        http.formLogin(); // 인증 문제시 로그인 화면
        http.csrf().disable(); // CSRF 동작 설정
        http.logout();

        // http.oauth2Login().successHandler(successHandler());
        http.rememberMe().tokenValiditySeconds(60*60*7).userDetailsService(userDetailsService()); // 7days

        // apiCheckFilter
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class); // 필터 동작 순서 조정
        // ApiLoginFilter
        http.addFilterBefore(apiLoginFilter(), UsernamePasswordAuthenticationFilter.class); // 필터 동작 순서 조정
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/movies"); // movies api 에 필터 적용
        apiLoginFilter.setAuthenticationManager(authenticationManager());

        // 인증 실패 시 ApiLoginFailHandler 처리
        apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());

        return apiLoginFilter;
    }

    // bean으로 apiCheckFilter 설정
    @Bean
    public ApiCheckFilter apiCheckFilter() {
        return new ApiCheckFilter("/movies"); //movies api 에 대해 필터 설정
    }

    // 자동으로 bean 등록되는 USerDetailsService 를 사용하기 위해 주석 처리
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 테스트용 사용자 1명 생성

        // 사용자 계정은 user1
        auth.inMemoryAuthentication().withUser("user1")
                .password("$2a$10$wbni5WOD2fKQ9UQirwkqRev2GQep.Ifdak9FdKL4aNrGgTe8Dbsma") // 1111 인코딩
                .roles("USER");
    }
    */
}
