package com.iiiiii.accountbook.review.command.application.controller;

import com.iiiiii.accountbook.accbook.command.domain.aggregate.entity.Accbook;
import com.iiiiii.accountbook.common.ResponseMessage;
import com.iiiiii.accountbook.common.ResponseStatusText;
import com.iiiiii.accountbook.review.command.application.service.StoreReviewService;
import com.iiiiii.accountbook.review.command.domain.aggregate.entity.StoreReview;
import com.iiiiii.accountbook.review.command.domain.aggregate.dto.StoreReviewDTO;
import com.iiiiii.accountbook.store.command.domain.aggregate.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;



@RestController("StoreReviewControllerCommand")
@RequestMapping("/storeReview")
public class StoreReviewController {

    private StoreReviewService storeReviewService;

    @Autowired
    public StoreReviewController(StoreReviewService storeReviewService) {
        this.storeReviewService = storeReviewService;
    }

    @PostMapping("/regist")
    public ResponseEntity<?> registStoreReview(@RequestBody StoreReviewDTO newStoreReview){
        StoreReview savedStoreReview = storeReviewService.registStoreReview(newStoreReview);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("storeReview", savedStoreReview);

        return ResponseEntity
                .ok(new ResponseMessage(ResponseStatusText.OK, responseMap));
    }


    @PutMapping("/modify/{storeReviewCode}")
    // 주석. @RequestBody(수정할 데이터 -> 메소드 수행 후 반환할 데이터) -  StoreReviewDTO 객체를 modifyStoreReview에 바인딩
    // 주석. @PathVariable(Url에서 추출할 값)
    public ResponseEntity<?> modifyStoreReview(@RequestBody StoreReviewDTO modifyStoreReview,
                                               @PathVariable Integer storeReviewCode){

        // 주석. 서비스 호출 및 결과 처리
        // 서비스의 메소드 호출하여 엔티티 수정
        StoreReview storeReview = storeReviewService.modifyStoreReview(storeReviewCode, modifyStoreReview);

        // 주석. 응답으로 보낼 데이터를 담기 위한 HashMap 객체 생성
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("storeReview", storeReview);

        // 주석. 응답 생성
        return ResponseEntity
                .ok(new ResponseMessage(ResponseStatusText.OK, responseMap));

    }

    @DeleteMapping("/remove/{storeReviewCode}")
    public ResponseEntity<?> deleteStoreReview(@PathVariable Integer storeReviewCode){

        storeReviewService.removeStoreReview(storeReviewCode);
        return ResponseEntity
                .noContent().build();
        
    }



}