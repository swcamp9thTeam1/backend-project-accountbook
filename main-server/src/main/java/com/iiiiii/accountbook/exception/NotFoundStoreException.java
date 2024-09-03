package com.iiiiii.accountbook.exception;

public class NotFoundStoreException extends Exception {

    public NotFoundStoreException() {
        super("존재하지 않는 가게입니다.");
    }
}
