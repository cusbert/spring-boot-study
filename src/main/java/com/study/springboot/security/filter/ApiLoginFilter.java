package com.study.springboot.security.filter;

import com.study.springboot.security.dto.AuthMemberDTO;
import com.study.springboot.security.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {

    // 로그인이 성공하면 클라이언트가 Authorization 헤더값으로 사용할 값 전송

    private JWTUtil jwtUtil;

    public ApiLoginFilter(String defaultFilterProcessesUrl, JWTUtil jwtUtil) {
        super(defaultFilterProcessesUrl);
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        log.info("----- ApiLoginFilter");
        log.info("attemptAuthentication start");

        String email = request.getParameter("email");
        String pw = request.getParameter("pw");

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, pw);

        if (email == null) {
            // email parameter 없으면 error 발생
            throw new BadCredentialsException("email cannot be null");
        }

        // AuthenticationManager 를 이용하여 인증 처리
        return getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // login 인증 성공 시 처리
        log.info("--- ApiLoginFilter");
        log.info("successfulAuthentication start");
        log.info("successfulAuthentication result" + authResult);
        log.info(authResult.getPrincipal());

        String email = ((AuthMemberDTO) authResult.getPrincipal()).getName();

        String token = null;

        try {
            token = jwtUtil.generateToken(email);

            response.setContentType("text/plain");
            response.getOutputStream().write(token.getBytes());

            log.info(token);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
