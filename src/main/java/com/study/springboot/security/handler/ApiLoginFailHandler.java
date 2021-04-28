package com.study.springboot.security.handler;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j2
public class ApiLoginFailHandler implements AuthenticationFailureHandler {
    // 인증 후 성공 or 실패에 따른 처리 handler
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("--- login fail handler...");
        log.info("exception" + exception.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response.setContentType("application/json;charset=utf-8");
        JSONObject json = new JSONObject();
        String message = exception.getMessage();
        json.put("code", "401");
        json.put("message", message);

        PrintWriter printWriter = response.getWriter();
        printWriter.print(json);
    }
}
