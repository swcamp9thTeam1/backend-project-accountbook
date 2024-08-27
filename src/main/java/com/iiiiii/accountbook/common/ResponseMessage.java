package com.iiiiii.accountbook.common;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseMessage {
    private ResponseStatusText statusText;      // 응답 상태 (OK, FAIL)
    private String message;                     // 응답 메시지
    private Map<String, Object> result;         // 응답 데이터

    public ResponseMessage(ResponseStatusText statusText, Map<String, Object> result) {
        this.statusText = statusText;
        this.result = result;
    }

    public ResponseMessage(String message) {
        this.message = message;
    }
}
