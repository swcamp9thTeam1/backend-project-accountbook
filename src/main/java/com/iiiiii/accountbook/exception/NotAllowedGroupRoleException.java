package com.iiiiii.accountbook.exception;

public class NotAllowedGroupRoleException extends Exception {

    public NotAllowedGroupRoleException() {
        super("승인 대기중인 그룹 멤버입니다.");
    }
}
