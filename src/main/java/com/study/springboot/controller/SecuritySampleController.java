package com.study.springboot.controller;

import com.study.springboot.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SecuritySampleController {

    @GetMapping("/all")
    public void exAll() {
        log.info("all...");
    }

    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        log.info("member...");
        log.info("==========");
        log.info(authMemberDTO);
        log.info(authMemberDTO.getAuthorities());
    }

    @GetMapping("/admin")
    public void exAdmin() {
        log.info("admin...");
    }
}
