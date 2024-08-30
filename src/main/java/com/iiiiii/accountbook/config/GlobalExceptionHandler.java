package com.iiiiii.accountbook.config;

import com.iiiiii.accountbook.common.ResponseError;
import com.iiiiii.accountbook.exception.NotValidRequestException;
import com.iiiiii.accountbook.store.exception.NotFoundStoreException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // status code 400
    @ExceptionHandler({
            NotValidRequestException.class,
            NotFoundStoreException.class
    })
    public ResponseEntity<ResponseError> handleBadRequest(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseError(e, e.getMessage()));
    }

    // status code 404
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseError> handleNotFound(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseError(e, e.getMessage()));
    }

    // status code 405
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseError> handleRequestMethodNotSupported(Exception e) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ResponseError(e, e.getMessage()));
    }

    // status code 500 (나머지 예외에 대해 처리)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleOthers(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseError(e, e.getMessage()));
    }
}
