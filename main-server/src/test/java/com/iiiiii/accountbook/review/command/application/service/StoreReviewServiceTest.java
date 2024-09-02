package com.iiiiii.accountbook.review.command.application.service;

import com.iiiiii.accountbook.review.command.domain.aggregate.dto.StoreReviewDTO;
import com.iiiiii.accountbook.review.command.domain.aggregate.entity.StoreReview;
import com.iiiiii.accountbook.review.command.domain.repository.StoreReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreReviewServiceTest {
    @Autowired
    StoreReviewService storeReviewService;
    @Autowired
    StoreReviewRepository storeReviewRepository;

    public static Stream<Arguments> addReviewInputDTO() {
        return Stream.of(
                Arguments.of("2024-08-25 14:32:00", 4, 250000L, "아주 좋음 ! 강추 ! .", 2, 3),
                Arguments.of("2024-08-28 20:00:00", 3, 180000L, "낫 배드 가성비 좋음 ", 3, 2)
        );
    }


    @DisplayName("가게 리뷰 추가 테스트")
    @ParameterizedTest  // 파라미터 테스트
    @MethodSource("addReviewInputDTO")   // 사용할 파라미터 저장
    public void testAddStoreReview(String createdAt, Integer visitors, Long totalExpense,
                                   String oneLineReview, Integer memberCode , Integer storeCode) {

        // 입력 값 set
        StoreReviewDTO addStoreReviewDTO = new StoreReviewDTO();
        addStoreReviewDTO.setCreatedAt(createdAt);
        addStoreReviewDTO.setVisitors(visitors);
        addStoreReviewDTO.setTotalExpense(totalExpense);
        addStoreReviewDTO.setOneLineReview(oneLineReview);
        addStoreReviewDTO.setMemberCode(memberCode);
        addStoreReviewDTO.setStoreCode(storeCode);

        // 입력 값 저장 -> 서비스의 메소드 이용
        StoreReview newStoreReview = storeReviewService.registStoreReview(addStoreReviewDTO);

        // 저장 확인
//        assertNotNUll(newStoreReview);
        assertNotNull(storeReviewRepository.findById(newStoreReview.getStoreReviewCode()));
    }

}