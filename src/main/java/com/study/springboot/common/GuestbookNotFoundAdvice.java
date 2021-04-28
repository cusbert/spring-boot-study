package com.study.springboot.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GuestbookNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(GuestbookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String guestbookNotFoundHandler(GuestbookNotFoundException ex) {
        return ex.getMessage();
    }
}
