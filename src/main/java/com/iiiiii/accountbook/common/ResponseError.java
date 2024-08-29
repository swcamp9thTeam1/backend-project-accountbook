package com.iiiiii.accountbook.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseError extends ResponseCommon {

    private final String errCode;

    public ResponseError(Exception e, String errMessage) {
        super(ResponseStatusText.FAIL, errMessage);
        this.errCode = e.getClass().getSimpleName();
    }
}
