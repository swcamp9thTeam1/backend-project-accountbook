package com.iiiiii.accountbook.exception;

public class NotGroupMemberException extends Exception {
    public NotGroupMemberException() {
        super("해당 그룹에 속하지 않는 멤버입니다.");
    }
}
