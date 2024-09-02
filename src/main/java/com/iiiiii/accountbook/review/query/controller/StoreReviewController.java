package com.iiiiii.accountbook.review.query.controller;


import com.iiiiii.accountbook.review.query.service.StoreReviewService;
import com.iiiiii.accountbook.review.query.dto.selectStoreReviewByMemberOrStoreCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class StoreReviewController {

    private  final StoreReviewService storeReviewService;

    @Autowired
    public StoreReviewController(StoreReviewService storeReviewService) {
        this.storeReviewService = storeReviewService;
    }

    @GetMapping("/member/{memberCode}")
    List<selectStoreReviewByMemberOrStoreCodeDTO> getReviewsByMemberCode(@PathVariable int memberCode) {
        return storeReviewService.findStoreReviewByMemberCode(memberCode);
    }

    @GetMapping("/store/{storeCode}")
    public List<selectStoreReviewByMemberOrStoreCodeDTO> getReviewsByStoreCode(@PathVariable int storeCode) {
        return storeReviewService.findStoreReviewByStoreCode(storeCode);
    }

     // 특정 예외를 처리하기 위한 ExceptionHandler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // 예외 발생 시 처리 로직
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return stringResponseEntity;
    }


}
