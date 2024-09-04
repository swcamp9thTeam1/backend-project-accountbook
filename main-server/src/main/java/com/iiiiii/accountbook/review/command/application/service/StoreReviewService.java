package com.iiiiii.accountbook.review.command.application.service;

import com.iiiiii.accountbook.asset.command.domain.aggregate.vo.RegisterReviewVO;
import com.iiiiii.accountbook.review.command.domain.aggregate.entity.StoreReview;
import com.iiiiii.accountbook.review.command.domain.repository.StoreReviewRepository;
import com.iiiiii.accountbook.review.command.domain.aggregate.dto.StoreReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service("StoreReviewServiceCommand")
@Slf4j
public class StoreReviewService {

    private StoreReviewRepository storeReviewRepository;

    @Autowired
    public StoreReviewService(StoreReviewRepository storeReviewRepository) {
        this.storeReviewRepository = storeReviewRepository;
    }

    @Transactional
    // 주석. 리뷰 서비스 - 리뷰 등록 메소드
    public StoreReview registStoreReview(RegisterReviewVO newStoreReview) {
        StoreReview storeReview = new StoreReview();

        // 리뷰 작성일시 설정
        storeReview.setCreatedAt(LocalDateTime.now().toString());

        // 나머지는 VO에서 가져오기
        storeReview.setVisitors(newStoreReview.getVisitors());
        storeReview.setTotalExpense(newStoreReview.getTotalExpense());
        storeReview.setOneLineReview(newStoreReview.getOneLineReview());
        storeReview.setMemberCode(newStoreReview.getMemberCode());
        storeReview.setStoreCode(newStoreReview.getStoreCode());

        storeReviewRepository.save(storeReview);
        return storeReview;
    }


    // 주석. 리뷰 서비스 - 리뷰 수정 메소드
    // 주석. 마이페이지 -> 리뷰 수정 기능
    // 주석. 2. 리뷰 서비스 ( Q. 두 가지 다 구현하냐?? ) -> 한번에 다 작성함
    @Transactional
    // 주석. 리뷰 수정 : 리뷰 코드 입력 -> 수정하도록 코드 작성
    // 주석. 리뷰 코드를 기준으로, DTO 수정 요청을 보낸다.
    public StoreReview modifyStoreReview(Integer reviewCode, StoreReviewDTO modifyStoreReview){

        StoreReview storeReview = storeReviewRepository.findById(reviewCode)
                .orElseThrow(() -> new IllegalArgumentException("리뷰가 없습니다."));


        storeReview.setCreatedAt(modifyStoreReview.getCreatedAt());
        storeReview.setVisitors(modifyStoreReview.getVisitors());
        storeReview.setTotalExpense(modifyStoreReview.getTotalExpense());
        storeReview.setOneLineReview(modifyStoreReview.getOneLineReview());
        storeReview.setMemberCode(modifyStoreReview.getMemberCode());
        storeReview.setStoreCode(modifyStoreReview.getStoreCode());

        return storeReviewRepository.save(storeReview);
    }

    // 주석. 리뷰 서비스 - 리뷰 삭제 메소드
    // 주석. 리뷰 코드 입력 -> 해당 리뷰 삭제
    // 엔티티 식별자를 통해 리뷰 삭제
    @Transactional
    public void removeStoreReview(Integer reviewCode){

        StoreReview storeReview = storeReviewRepository.findById(reviewCode).orElseThrow(IllegalArgumentException::new);
        storeReviewRepository.delete(storeReview);
    }


}
