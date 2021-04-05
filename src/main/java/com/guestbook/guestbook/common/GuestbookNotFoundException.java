package com.guestbook.guestbook.common;

public class GuestbookNotFoundException extends RuntimeException {

    public GuestbookNotFoundException(Long id) {
        super("Could not found " + id);
    }
}
