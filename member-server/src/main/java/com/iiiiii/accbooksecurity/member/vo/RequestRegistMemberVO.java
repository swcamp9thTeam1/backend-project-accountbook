package com.iiiiii.accbooksecurity.member.vo;

import lombok.Data;

@Data
public class RequestRegistMemberVO {

    private int memberCode;
    private String memberId;
    private String memberNickname;
    private String memberEmail;
    private String password;
    private String memberIsAdmin;
    private Long memberMonthlyBudget;
}
