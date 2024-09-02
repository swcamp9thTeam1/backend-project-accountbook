package com.iiiiii.accountbook.review.query.service;

import com.iiiiii.accountbook.review.query.dto.selectStoreReviewByMemberOrStoreCodeDTO;
import com.iiiiii.accountbook.review.query.repository.StoreReviewMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreReviewService {

    // 주석. 서비스 - 매퍼 연결 -> 생성자 주입
    private final StoreReviewMapper storeReviewMapper;

    public StoreReviewService(StoreReviewMapper storeReviewMapper) {
        this.storeReviewMapper = storeReviewMapper;
    }


    // 주석. 2. 리뷰 서비스 - 리뷰 조회 메소드
    // 주석. 2-1. 회원이 마이페이지에서 자신의 리뷰 조회 -> 회원 코드로 리뷰 조회
    public List<selectStoreReviewByMemberOrStoreCodeDTO> findStoreReviewByMemberCode(int memberCode){
        return storeReviewMapper.selectReviewByMemberCode(memberCode);
    }

    // 주석. 2. 리뷰 서비스 - 리뷰 조회 메소드
    // 주석. 2-2. 지도를 통해 리뷰 조회 -> 가게 코드로 리뷰 조회
    public List<selectStoreReviewByMemberOrStoreCodeDTO> findStoreReviewByStoreCode(int storeCode){
        return storeReviewMapper.selectReviewByStoreCode(storeCode);
    }




}
