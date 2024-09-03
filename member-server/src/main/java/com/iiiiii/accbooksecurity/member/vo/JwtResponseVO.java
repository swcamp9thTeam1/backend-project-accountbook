package com.iiiiii.accbooksecurity.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class JwtResponseVO {

        private String token;
        private String refreshToken;

        public JwtResponseVO(String token) {
                this.token = token;
        }

        public JwtResponseVO(String token, String refreshToken) {
                this.token = token;
                this.refreshToken = refreshToken;
        }
}

