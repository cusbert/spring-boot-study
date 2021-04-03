package com.guestbook.guestbook.common;

public class GuestbookNotFoundException extends RuntimeException {

    public GuestbookNotFoundException(Long gno) {
        super("Could not found guestbook " + gno);
    }
}
