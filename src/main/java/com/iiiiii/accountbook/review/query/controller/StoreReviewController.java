package com.iiiiii.accountbook.review.query.controller;

import com.iiiiii.accountbook.review.query.service.StoreReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class StoreReviewController {

//    @Autowired
//    private StoreReviewService storeReviewService;
//
//    // 리뷰 추가
//    @PostMapping("/add")
//    public String addReview(
//            @RequestParam int memberCode,
//            @RequestParam int storeCode,
//            @RequestParam int visitors,
//            @RequestParam long totalExpense,
//            @RequestParam String oneLineReview) {
//        try {
//            storeReviewService.addReview( visitors, totalExpense, oneLineReview, memberCode, storeCode);
//            return "리뷰가 성공적으로 추가되었습니다.";
//        } catch (Exception e) {
//            return "리뷰 추가 중 오류가 발생했습니다.";
//        }
//    }
//
//
//    @GetMapping("/find")    // 특정 회원의 정보를 요청받아서 회원의 리뷰 조회 ?
//    public String findOneMemberReview() {
//        try {
//            return "회원님의 리뷰가 조회되었습니다. ";
//
//        } catch (Exception e) {
//            return "리뷰 조회 중 오류가 발생했습니다.";
//        }
//    }
//
//
//
////    @GetMapping("/modify")      // 특정 회원의 특정 리뷰 수정 ?
////    public String modifyReview(
////    )
}
