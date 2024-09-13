package com.iiiiii.accountbook.review.command.application.service;

import com.iiiiii.accountbook.review.command.domain.aggregate.vo.RegisterReviewVO;
import com.iiiiii.accountbook.review.command.domain.aggregate.dto.StoreReviewDTO;
import com.iiiiii.accountbook.review.command.domain.aggregate.entity.StoreReview;
import com.iiiiii.accountbook.review.command.domain.repository.StoreReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreReviewServiceTest {
    @Autowired
    StoreReviewService storeReviewService;
    @Autowired
    StoreReviewRepository storeReviewRepository;


    /* 주석. 테스트코드. 1. 리뷰 추가 서비스 테스트코드 */
    // 테스트 메소드에 제공할 데이터 소스 반환 메소드
    // Stream<Arguments> 반환 -> 각 Arguments가 testAddStoreReview메소드에 전달될 테스트 데이터 세트
    public static Stream<Arguments> provideReviewDTO() {
        return Stream.of(
                Arguments.of(4, 250000L, "아주 좋음 ! 강추 ! .", 2, 3),
                Arguments.of(3, 180000L, "낫 배드 가성비 좋음 ", 3, 2)
        );
    }
    @DisplayName("가게 리뷰 추가 테스트")
    @ParameterizedTest  // 파라미터 테스트
    @MethodSource("provideReviewDTO")   // 사용할 파라미터 저장
    public void testAddStoreReview(Integer visitors, Long totalExpense,
                                   String oneLineReview, Integer memberCode , Integer storeCode) {

        // 입력 값 set
        RegisterReviewVO addStoreReviewVO = new RegisterReviewVO(
                visitors, totalExpense, oneLineReview, memberCode, storeCode
        );

        // registStoreReview 메소드 동작 확인
        StoreReview newStoreReview = storeReviewService.registStoreReview(addStoreReviewVO);

        // 저장 확인
        assertNotNull(newStoreReview);
        assertNotNull(storeReviewRepository.findById(newStoreReview.getStoreReviewCode()));
    }



    // 수정할 값
    public static Stream<Arguments> provideReviewDTO2(){
        return Stream.of(
                Arguments.of("2024-09-09", 5, 30000L, "좋구만요", 1, 1),
                Arguments.of("2024-09-09", 5, 30000L, "맛있어요! ", 2, 2),
                Arguments.of("2024-09-09", 5, 30000L, "가성비가 엄청 좋아요 ! ", 3, 3)
        );
    }

    /* 주석. 테스트코드. 2. 리뷰 수정 서비스 테스트 코드 */
    @DisplayName("라뷰 수정 테스트")
    @ParameterizedTest
    @MethodSource("provideReviewDTO2")
    void testModifyStoreReview(String createdAt, Integer visitors, Long totalExpense,
                               String oneLineReview, Integer memberCode , Integer storeCode){

        // given (테스트 상황 설정)
        // 수정할 리뷰 엔티티와 DTO 객체 생성
        // 초기 엔티티 (수정 전 값)
        StoreReview initialStoreReview = new StoreReview();
        initialStoreReview.setCreatedAt(createdAt);
        initialStoreReview.setVisitors(5);
        initialStoreReview.setTotalExpense(80000L);
        initialStoreReview.setOneLineReview("맛도 최고 기분도 최고 v^0^v");
        initialStoreReview.setMemberCode(memberCode);
        initialStoreReview.setStoreCode(storeCode);

        // DB에 저장
        storeReviewRepository.save(initialStoreReview);

        // DTO (수정할 데이터)
        StoreReviewDTO modifyStoreReviewDTO = new StoreReviewDTO();
        modifyStoreReviewDTO.setCreatedAt(createdAt);
        modifyStoreReviewDTO.setVisitors(visitors);
        modifyStoreReviewDTO.setTotalExpense(totalExpense);
        modifyStoreReviewDTO.setOneLineReview(oneLineReview);
        modifyStoreReviewDTO.setMemberCode(memberCode);
        modifyStoreReviewDTO.setStoreCode(storeCode);


        // when (서비스의 메소드 호출로 엔티티 수정)
        // modifiedStoreReviewEntity: 실제로 수정된 엔티티의 결과
        StoreReview modifiedStoreReviewEntity = storeReviewService.modifyStoreReview(initialStoreReview.getStoreReviewCode(), modifyStoreReviewDTO);

        // then(modifyStoreReview) -> given 와 when(modifiedStoreReviewEntity) 을 비교
        assertEquals(modifiedStoreReviewEntity.getCreatedAt(), modifyStoreReviewDTO.getCreatedAt());
        assertEquals(modifiedStoreReviewEntity.getVisitors(), modifyStoreReviewDTO.getVisitors());
        assertEquals(modifiedStoreReviewEntity.getTotalExpense(), modifyStoreReviewDTO.getTotalExpense());
        assertEquals(modifiedStoreReviewEntity.getOneLineReview(), modifyStoreReviewDTO.getOneLineReview());
        assertEquals(modifiedStoreReviewEntity.getMemberCode(), modifyStoreReviewDTO.getMemberCode());
        assertEquals(modifiedStoreReviewEntity.getStoreCode(), modifyStoreReviewDTO.getStoreCode());

    }
    /* 주석. 테스코드. 3. 가게 리뷰 수정 시, 예외가 올바르게 발생하는지 검증하는 테스트코드 */
    @DisplayName("가게 리뷰 수정 예외 발생 검증 테스트")
    @Test
    public void testAddStoreReviewException() {

        // DTO (수정할 데이터)
        StoreReviewDTO modifyStoreReviewDTO = new StoreReviewDTO();
        modifyStoreReviewDTO.setCreatedAt("2024-09-10");
        modifyStoreReviewDTO.setVisitors(100);
        modifyStoreReviewDTO.setTotalExpense(5000L);
        modifyStoreReviewDTO.setOneLineReview("맛이 좋습니다요");
        modifyStoreReviewDTO.setMemberCode(1);
        modifyStoreReviewDTO.setStoreCode(1);


        // when
        // then
        // 여기서 잘못된 번호를 조회 -> "리뷰가 없습니다." 가 나와야함(잘못된 리뷰 코드로 조회)
        // 설정. 존재하지 않는 9999번 리뷰를 조회한다.
        // IllegalArgumentException이 올바르게 나오는지 확인 (문구 확인)
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            storeReviewService.modifyStoreReview(9999, modifyStoreReviewDTO);
        });
        assertEquals("리뷰가 없습니다." , exception.getMessage());
    }


    /* 주석. 테스트코드. 4.  리뷰 삭제 서비스 테스트 코드 */
    @DisplayName("리뷰 삭제 테스트")
    @ParameterizedTest
    @MethodSource("provideReviewDTO2")
    void testRemoveStoreReview(String createdAt, Integer visitors, Long totalExpense,
                               String oneLineReview, Integer memberCode , Integer storeCode){
        // given 초기 설정
        // 초기 엔티티
        StoreReview initialStoreReview = new StoreReview();
        initialStoreReview.setCreatedAt(createdAt);
        initialStoreReview.setVisitors(visitors);
        initialStoreReview.setTotalExpense(totalExpense);
        initialStoreReview.setOneLineReview(oneLineReview);
        initialStoreReview.setMemberCode(memberCode);
        initialStoreReview.setStoreCode(storeCode);

        // DB에 저장
        storeReviewRepository.save(initialStoreReview);
        // 삭제할 리뷰 코드
        int removeReviewCode = initialStoreReview.getStoreReviewCode();

        // 삭제이므로 DTO 필요 없음

        // when 메소드 호출 -> 엔티티 수정
        storeReviewService.removeStoreReview(removeReviewCode);


        // then 결과 점검
        assertFalse(storeReviewRepository.existsById(removeReviewCode));
    }

    /* 주석. 테스트코드. 5. 리뷰 삭제 서비스 테스트의 예외 발생이 잘 작동하는지 확인하기 위한 테스트코드 */
    // 리뷰 목록에 없는 리뷰를 삭제하고자 하는 경우 예외가 잘 작동하는지 확인
    // 9999번 리뷰 코드 조회 -> 목록에 없으므로 예외 (IllegalArgumentException) 발생
    @DisplayName("리뷰 삭제 예외 발생 테스트코드")
    @Test
    void testRemoveStoreReviewException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            storeReviewService.removeStoreReview(9999);
        });
        assertEquals("리뷰가 존재하지 않습니다." ,exception.getMessage());
    }






}