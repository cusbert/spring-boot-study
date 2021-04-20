package com.guestbook.guestbook.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode() {
        String password = "1111";
        String enPassword = passwordEncoder.encode(password);

        System.out.println("enPassword : " + enPassword);

        boolean matchResult = passwordEncoder.matches(password, enPassword);
        System.out.println("matchResult : " + matchResult);
    }
}
