package com.iiiiii.accountbook.common;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseMessageGeneric<T> {
    private ResponseStatusText statusText;
    private String message;
    private T result;

    public ResponseMessageGeneric(ResponseStatusText statusText, T result) {
        this.statusText = statusText;
        this.result = result;
    }
}
