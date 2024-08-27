package com.iiiiii.accountbook.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseError extends ResponseMessage {

    private final String errCode;

    public ResponseError(Exception e, String errMessage) {
        super(errMessage);
        this.errCode = e.getClass().getSimpleName();
        setStatusText(ResponseStatusText.FAIL);
    }
}
