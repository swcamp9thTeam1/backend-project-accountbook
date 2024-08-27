package com.iiiiii.accountbook.review.query.service;

import com.iiiiii.accountbook.review.query.dto.StoreReviewDTO;
import com.iiiiii.accountbook.review.query.repository.StoreReviewMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class StoreReviewServiceTest {
    @Autowired
    private StoreReviewService storeReviewService;
    @Autowired
    private StoreReviewMapper storeReviewMapper;


    @DisplayName("리뷰추가여부_Y선택_리뷰저장")
    @Test
    public void addReviewTestWhenYesOrNOIsTrue(){
        // 객체 생성
        StoreReviewDTO storeReviewDTO = new StoreReviewDTO();
        storeReviewDTO.setCreatedAt(LocalDateTime.now());
        storeReviewDTO.setVisitors(6);
        storeReviewDTO.setTotalExpense(580000);
        storeReviewDTO.setOneLineReview("저렴하게 잘 먹었어요~!");
        storeReviewDTO.setMemberCode(3);
        storeReviewDTO.setStoreCode(4);


        // 리뷰 추가
        storeReviewService.addReview(storeReviewDTO, true);

        // 리뷰 검증
        StoreReviewDTO result = storeReviewMapper.selectReviewById(storeReviewDTO.getCode());
        assertNotNull(result, "리뷰가 추가되지 않았습니다.");
        assertEquals(storeReviewDTO.getCode(), result.getCode());
        assertEquals(6, result.getVisitors());
        assertEquals(580000, result.getTotalExpense());
        assertEquals("저렴하게 잘 먹었어요~!", result.getOneLineReview());
        assertEquals(3, result.getMemberCode());
        assertEquals(4, result.getStoreCode());




    }


}