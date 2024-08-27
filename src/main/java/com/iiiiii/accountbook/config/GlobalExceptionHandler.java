package com.iiiiii.accountbook.config;

import com.iiiiii.accountbook.common.ResponseError;
import com.iiiiii.accountbook.exception.NotValidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 500 에러에 대한 공통 처리
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseError> internalServerErrorHandler(Exception e) {
        e.printStackTrace();

        return new ResponseEntity<>(
                new ResponseError(e, e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
