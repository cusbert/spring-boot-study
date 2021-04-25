package com.guestbook.guestbook.security;

import com.guestbook.guestbook.security.util.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

public class JWTTests {

    private JWTUtil jwtUtil;

    @BeforeEach
    public void testBefore() {
        System.out.println("--- testBefore...");
        jwtUtil = new JWTUtil();
    }

    @Test
    public void testEncode() throws Exception {
        String email = "mail40@mail.com";
        String str = jwtUtil.generateToken(email);

        System.out.println("---");
        System.out.println(str);
    }

    @Test
    public void testValidate() throws Exception {
        String email = "mail40@mail.com";
        String str = jwtUtil.generateToken(email);

        Thread.sleep(5000);
        String result = jwtUtil.validateAndExtract(str);
        System.out.println("--- result");
        System.out.println(result);
    }
}
