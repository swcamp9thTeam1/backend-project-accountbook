package com.iiiiii.accountbook.exception;

public class NotFullySuccessRegisterGoodStoreException extends Exception {

    public NotFullySuccessRegisterGoodStoreException() {
        super("착한가격 업소 등록이 완전히 성공하지 못했습니다. (일부만 등록됨)");
    }
}
