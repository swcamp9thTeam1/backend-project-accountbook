package com.iiiiii.accountbook.member.query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private int memberCode;
    private String memberId;
    private String email;
    private String memberPassword;
    private String nickname;
    private String isAdmin = "N";  // 기본값으로 "N" 설정
    private String monthlyBudget = "0";  // 기본값으로 "0" 설정

}
