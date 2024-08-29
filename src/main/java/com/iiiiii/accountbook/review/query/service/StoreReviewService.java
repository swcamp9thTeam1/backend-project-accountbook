package com.iiiiii.accountbook.review.query.service;

import com.iiiiii.accountbook.review.query.dto.StoreReviewDTO;
import com.iiiiii.accountbook.review.query.repository.StoreReviewMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StoreReviewService {

//    @Autowired
//    private  StoreReviewMapper storeReviewMapper;
//
//
//    // 주석. 리뷰 id로 조회
//    public StoreReviewDTO getReviewById(int reviewCode) {
//        try{
//            StoreReviewDTO storeReviewDTO = storeReviewMapper.selectReviewById(reviewCode);
//            log.info("리뷰를 가져옵니다... : {}", storeReviewDTO);
//            return storeReviewDTO;
//        } catch(Exception e){
//            log.error("리뷰 조회 중 오류가 발생하였습니다. : {} ", e);
//            throw new RuntimeException("리뷰 조회 실패", e);
//
//        }
//    }
//
//    // 주석. 리뷰 추가
//    // 주석. 가계부 작성 시 -> 리뷰 작성
//    // 주석. 가계부 테이블에서 -> 멤버코드, 가게 코드 가져온다.
//    // 주석. 사용자가 입력하는 값은 -> 방문자수, 지출, 한줄리뷰
//    // 주석. 기본키는 알아서 생성 -> auto_increment
//
//    public void addReview( int visitors, long totalExpense, String oneLineReview, int memberCode, int storeCode) {
//        try {
//            StoreReviewDTO storeReviewDTO = new StoreReviewDTO();
//            storeReviewDTO.setCreatedAt(java.time.LocalDateTime.now());
//            storeReviewDTO.setVisitors(visitors);
//            storeReviewDTO.setTotalExpense(totalExpense);
//            storeReviewDTO.setOneLineReview(oneLineReview);
//            storeReviewDTO.setMemberCode(memberCode);
//            storeReviewDTO.setStoreCode(storeCode);
//
//            storeReviewMapper.insertReview(storeReviewDTO);
//            log.info("리뷰가 성공적으로 추가되었습니다. : {}", storeReviewDTO);
//        } catch (Exception e) {
//            log.error("리뷰 추가 중 오류가 발생하였습니다. : ", e);
//            throw new RuntimeException("리뷰 추가에 실패했습니다.", e);
//        }
//    }
////    // 주석. 특정 회원이 작성한 모든 리뷰 조회
////    // 주석. 회원 코드 받아서 조회
////
////    public StoreReviewDTO getMemberReviews(int memberCode){
////        StoreReviewDTO storeReviewDTO = new StoreReviewDTO();
////        List<Integer>reviewCodes = storeReviewMapper.selectAllReviewCodeFromOneMember(memberCode);
////        for(Integer reviewCode : reviewCodes){
////            getReviewById(reviewCode);
////        }
////        return storeReviewDTO;
////    }
//
//
//    // 주석. 특정 회원이 고른 특정 리뷰 조회 -> 수정 , 삭제 기능
//    // 주석. 회원 & 리뷰코드 모두 선택
//public StoreReviewDTO getReviewByMemberAndCode(int memberCode, int reviewCode) {
//    try {
//        StoreReviewDTO storeReviewDTO = storeReviewMapper.selectReviewByMemberAndCode(memberCode, reviewCode);
//        if (storeReviewDTO != null) {
//            log.info("회원 {}의 리뷰 {}를 가져왔습니다: {}", memberCode, reviewCode, storeReviewDTO);
//            return storeReviewDTO;
//        } else {
//            log.warn("회원 {}의 리뷰 {}를 찾을 수 없습니다.", memberCode, reviewCode);
//            throw new RuntimeException("리뷰를 찾을 수 없습니다.");
//        }
//    } catch (Exception e) {
//        log.error("회원 {}의 리뷰 {} 조회 중 오류 발생: {}", memberCode, reviewCode, e);
//        throw new RuntimeException("리뷰 조회 실패", e);
//    }
//}
//
//
//    // 주석. 리뷰 수정 로직
//    // 주석. 특정 회원이 작성한 리뷰 모두 조회
//    // 주석. 이 중 회원이 수정하고싶은 리뷰 코드 선택
//    // 주석. 리뷰 수정
//    public void modifyReview(int reviewCode, int visitors, long totalExpense, String oneLineReview){
//        StoreReviewDTO storeReviewDTO = getReviewById(reviewCode);
//
//    }
////    // 주석. 리뷰 삭제
////    public void deleteReview(int reviewCode){
////
////        StoreReviewDTO getDeleteReviewDTO = storeReviewMapper.selectReviewById(reviewCode);
////    }
//
//



}
