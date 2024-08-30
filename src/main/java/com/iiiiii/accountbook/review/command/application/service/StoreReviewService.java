package com.iiiiii.accountbook.review.command.application.service;

import com.iiiiii.accountbook.review.command.domain.aggregate.entity.StoreReview;
import com.iiiiii.accountbook.review.command.domain.repository.StoreReviewRepository;
import com.iiiiii.accountbook.review.command.domain.aggregate.dto.StoreReviewDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public StoreReview registStoreReview(StoreReviewDTO newStoreReview) {
        StoreReview storeReview = new StoreReview();
        storeReview.setCreatedAt(newStoreReview.getCreatedAt());
        storeReview.setVisitors(newStoreReview.getVisitors());
        storeReview.setTotalExpense(newStoreReview.getTotalExpense());
        storeReview.setOneLineReview(newStoreReview.getOneLineReview());
        storeReview.setMemberCode(newStoreReview.getMemberCode());
        storeReview.setStoreCode(newStoreReview.getStoreCode());

        storeReviewRepository.save(storeReview);
        return storeReview;
    }


}
