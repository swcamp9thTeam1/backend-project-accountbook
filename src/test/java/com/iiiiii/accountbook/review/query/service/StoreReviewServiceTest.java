package com.iiiiii.accountbook.review.query.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreReviewServiceTest {

    @Autowired
    private StoreReviewService storeReviewService;

    // 멤버 코드로 리뷰 조회하는 서비스
    // findStoreReviewByMemberCode
    private static Stream<Arguments> provideMemberCode() {
        return Stream.of(
                Arguments.of(1)
        );
    }

    // 가게 코드로 리뷰 조회하는 서비스
    // findStoreReviewByStoreCode
    private static Stream<Arguments> provideStoreCode(){
        return Stream.of(
                Arguments.of(1)
        );
    }

    @DisplayName("멤버 코드로 가게 리뷰 조회 테스트")
    @ParameterizedTest
    @MethodSource("provideMemberCode")
    public void testFindReviewByMemberCode(Integer memberCode) {
        assertDoesNotThrow(() -> storeReviewService.findStoreReviewByMemberCode(memberCode));
    }

    
    @DisplayName("가게 코드로 가게 리뷰 조회 테스트")
    @ParameterizedTest
    @MethodSource("provideStoreCode")
    public void testFindReviewByStoreCode(Integer storeCode) {
        assertDoesNotThrow(() -> storeReviewService.findStoreReviewByStoreCode(storeCode));

    }




}