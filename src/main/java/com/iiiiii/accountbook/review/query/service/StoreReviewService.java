package com.iiiiii.accountbook.review.query.service;

import com.iiiiii.accountbook.review.query.dto.StoreReviewDTO;
import com.iiiiii.accountbook.review.query.repository.StoreReviewMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StoreReviewService {

    @Autowired
    private  StoreReviewMapper storeReviewMapper;

    // 주석. 리뷰 추가
    // 주석. 리뷰 추가 여부 yesOrNo boolean으로 저장 -> true인 경우 리뷰 저장
    public void addReview(StoreReviewDTO storeReviewDTO , boolean yesOrNO){
        if(yesOrNO){
            try {
                storeReviewMapper.insertReview(storeReviewDTO);
                log.info("리뷰가 성공적으로 추가되었습니다. : {}", storeReviewDTO);
            } catch(Exception e) {
                log.error("리뷰 추가 중 오류 발생하였습니다. : ",  e);
            }
        } else{
            log.info("리뷰 추가가 취소되었습니다. ");
        }
    }

    // 주석. 리뷰 수정 로직
    // 주석. 리뷰



}
