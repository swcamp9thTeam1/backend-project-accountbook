package com.iiiiii.accbooksecurity.member.command.domain.aggregate.dto;

import lombok.*;

@Data
public class MemberDTO {

    private int memberCode;
    private String memberId;
    private String memberNickname;
    private String memberEmail;
    private String password;  // 평문으로 저장, 엔티티로 변환 시 암호화
    private String memberIsAdmin;
    private Long memberMonthlyBudget;
}
