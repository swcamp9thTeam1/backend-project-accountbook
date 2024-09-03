package com.iiiiii.accbookserver.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class ResponseMessage extends ResponseCommon {
    private Map<String, Object> result;         // 응답 데이터

    public ResponseMessage(ResponseStatusText statusText, Map<String, Object> responseMap) {
        super(statusText);
        this.result = responseMap;
    }

    public ResponseMessage(Map<String, Object> result) {
        super(ResponseStatusText.OK);
        this.result = result;
    }

    public ResponseMessage(String message, Map<String, Object> result) {
        super(ResponseStatusText.OK, message);
        this.result = result;
    }

    public ResponseMessage(ResponseStatusText statusText, String message,
                           Map<String, Object> result) {
        super(statusText, message);
        this.result = result;
    }
}
