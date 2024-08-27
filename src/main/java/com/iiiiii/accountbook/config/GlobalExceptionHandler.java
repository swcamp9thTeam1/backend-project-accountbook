package com.iiiiii.accountbook.config;

import com.iiiiii.accountbook.common.ResponseError;
import com.iiiiii.accountbook.exception.NotValidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 500 에러에 대한 공통 처리
    @ExceptionHandler(
            NotValidRequestException.class      // 유효하지 않은 Request 데이터
    )
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseError> internalServerErrorHandler(Exception e) {
        return new ResponseEntity<>(
                new ResponseError(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
