package com.iiiiii.accbooksecurity.member.vo;

import lombok.Data;

@Data
public class ResponseRegistMemberVO {

    private int code;
    private String memberId;
    private String memberNickname;
    private String memberEmail;
    private String memberIsAdmin;
    private Long memberMonthlyBudget;
}
