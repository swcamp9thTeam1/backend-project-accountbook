package com.iiiiii.accbooksecurity.member.vo;

import com.iiiiii.accbooksecurity.member.command.domain.aggregate.dto.MemberDTO;
import lombok.*;

@Data
public class JwtResponseVO {

        private int memberCode;
        private String memberId;
        private String memberNickname;
        private String memberEmail;
        private String memberIsAdmin;
        private Long memberMonthlyBudget;
        private String token;
        private String refreshToken;

        public JwtResponseVO(String token) {
                this.token = token;
        }

        public JwtResponseVO(String token, String refreshToken) {
                this.token = token;
                this.refreshToken = refreshToken;
        }


        public JwtResponseVO(String token, MemberDTO memberDTO) {
                this.token = token;
                this.memberCode = memberDTO.getMemberCode();
                this.memberId = memberDTO.getMemberId();
                this.memberNickname = memberDTO.getMemberNickname();
                this.memberEmail = memberDTO.getMemberEmail();
                this.memberIsAdmin = memberDTO.getMemberIsAdmin();
                this.memberMonthlyBudget = memberDTO.getMemberMonthlyBudget();
        }

}

