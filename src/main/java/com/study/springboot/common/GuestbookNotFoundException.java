package com.study.springboot.common;

public class GuestbookNotFoundException extends RuntimeException {

    public GuestbookNotFoundException(Long id) {
        super("Could not found " + id);
    }
}
