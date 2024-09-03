package com.iiiiii.accbooksecurity.member.vo;

import lombok.Data;

@Data
public class RequestRegistMemberVO {

    /* 설명. 회원가입 전달 */
    private String memberId;
    private String memberNickname;
    private String memberEmail;
    private String password;
    private String memberIsAdmin;
    private Long memberMonthlyBudget;
}
