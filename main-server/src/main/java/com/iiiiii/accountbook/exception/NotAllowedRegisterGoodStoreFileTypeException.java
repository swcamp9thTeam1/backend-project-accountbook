package com.iiiiii.accountbook.exception;

public class NotAllowedRegisterGoodStoreFileTypeException extends Exception {

    public NotAllowedRegisterGoodStoreFileTypeException() {
        super("착한 가게 등록은 엑셀 파일만 가능합니다.");
    }
}
