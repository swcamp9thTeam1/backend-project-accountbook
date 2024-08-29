package com.iiiiii.accountbook.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class ResponseMessage extends ResponseCommon {
    private Map<String, Object> result;         // 응답 데이터

    public ResponseMessage(ResponseStatusText ok, Map<String, Object> responseMap) {}

    public ResponseMessage(Map<String, Object> result) {
        super(ResponseStatusText.OK);
        this.result = result;
    }

    public ResponseMessage(String message, Map<String, Object> result) {
        super(ResponseStatusText.OK, message);
        this.result = result;
    }
}
