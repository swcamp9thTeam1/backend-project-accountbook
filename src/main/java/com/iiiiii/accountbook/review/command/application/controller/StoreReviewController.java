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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
