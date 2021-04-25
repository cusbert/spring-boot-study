package com.guestbook.guestbook.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import io.jsonwebtoken.impl.DefaultJwt;
import lombok.extern.log4j.Log4j2;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

@Log4j2
public class JWTUtil {
    private String secretKey = "testsecret12345";

    private long expire = 60 * 24 * 30; // 1 month

    public String generateToken(String content) throws UnsupportedEncodingException {
        log.info("--- generateToken");
        // Token 생성
        return Jwts.builder()
                .setIssuedAt(new Date())
               // .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant())) // 만료 설정
                .setExpiration(Date.from(ZonedDateTime.now().plusSeconds(1).toInstant())) // 만료 설정
                .claim("sub", content) // email 넣을 예정
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8")) // secret Key로 Signature 생성
                .compact();
    }

    public String validateAndExtract(String tokenStr) {
        // JWT 문자열이 유효한지 검증 - 만료 기간 등
        // JSON Web Signature (JWS)
        log.info("--- validateAndExtract");

        String contentValue = null;
        try {
            DefaultJws defaultJws = (DefaultJws) Jwts.parser()
                    .setSigningKey(secretKey.getBytes("UTF-8"))
                    .parseClaimsJws(tokenStr);

            log.info("defaultJwt : " + defaultJws);
            log.info(defaultJws.getBody().getClass());

            DefaultClaims claims = (DefaultClaims) defaultJws.getBody();
            log.info("--------");
            log.info("DefaultClaims : " + claims);
            contentValue = claims.getSubject();

        } catch (Exception e) {

            e.printStackTrace();
            log.error(e.getMessage());
            contentValue = null;
        }

        return contentValue;
    }

}
