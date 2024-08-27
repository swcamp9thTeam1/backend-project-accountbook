package com.iiiiii.accountbook.common;

public class ResponseError extends ResponseMessage {

    public ResponseError(String errMessage) {
        super(errMessage);
        setStatusText(ResponseStatusText.FAIL);
    }
}
