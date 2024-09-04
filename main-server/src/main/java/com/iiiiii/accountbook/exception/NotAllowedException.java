package com.iiiiii.accountbook.exception;

public class NotAllowedException extends Exception {

    public NotAllowedException(String message) {
        super(message + " 권한이 없습니다.");
    }
}
