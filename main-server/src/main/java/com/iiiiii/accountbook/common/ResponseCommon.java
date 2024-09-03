package com.iiiiii.accountbook.common;

import lombok.*;

@Getter
@Setter
@ToString
public class ResponseCommon {
    private ResponseStatusText statusText;      // 응답 상태 (OK, FAIL)
    private String message;                     // 응답 메시지

    public ResponseCommon() {}

    public ResponseCommon(ResponseStatusText statusText) {
        this.statusText = statusText;
    }

    public ResponseCommon(ResponseStatusText statusText, String message) {
        this.statusText = statusText;
        this.message = message;
    }
}
