package com.iiiiii.accountbook.exception;

public class EmptyResultSearchStoreException extends Exception {

    public EmptyResultSearchStoreException() {
        super("조건에 맞는 가게를 찾을 수 없습니다.");
    }
}
