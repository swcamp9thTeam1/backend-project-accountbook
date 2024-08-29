package com.iiiiii.accountbook.common;

import lombok.*;

@Getter
@Setter
@ToString
public class ResponseMessageGeneric<T> extends ResponseCommon {
    private T result;

    public ResponseMessageGeneric() {}

    public ResponseMessageGeneric(T result) {
        setStatusText(ResponseStatusText.OK);
        this.result = result;
    }
}
