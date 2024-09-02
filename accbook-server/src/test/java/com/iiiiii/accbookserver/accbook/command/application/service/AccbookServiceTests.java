package com.iiiiii.accbookserver.accbook.command.application.service;

import com.iiiiii.accountbook.accbook.command.domain.aggregate.dto.AccbookDTO;
import com.iiiiii.accountbook.accbook.command.domain.aggregate.entity.Accbook;
import com.iiiiii.accountbook.accbook.command.domain.repository.AccbookRepository;
import com.iiiiii.accountbook.common.YesOrNo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class AccbookServiceTests {
    @Autowired
    private AccbookService accbookService;

    @Autowired
    private AccbookRepository accbookRepository;

    private static Stream<Arguments> provideAccbook() {
        return Stream.of(
                Arguments.of( "2024-08-29", "쿠팡", 58000L, YesOrNo.N, 1, 1, null, 1),
                Arguments.of( "2024-08-30", "웨이브", 10000L, YesOrNo.Y, 1, 2, null, 2)
        );
    }

    @DisplayName("가계부 작성 테스트")
    @ParameterizedTest
    @MethodSource("provideAccbook")
    void testRegistAccbook(String createdAt, String title, Long amount, YesOrNo isRegular,
                                  Integer memberCode, Integer accCategoryCode, Integer storeCode, Integer assetCode) {
        // given
        AccbookDTO newAccbookDTO = new AccbookDTO();
        newAccbookDTO.setCreatedAt(createdAt);
        newAccbookDTO.setTitle(title);
        newAccbookDTO.setAmount(amount);
        newAccbookDTO.setIsRegular(isRegular);
        newAccbookDTO.setMemberCode(memberCode);
        newAccbookDTO.setAccCategoryCode(accCategoryCode);
        newAccbookDTO.setStoreCode(storeCode);
        newAccbookDTO.setAssetCode(assetCode);

        // when
        Accbook actualAccbook = accbookService.registAccbook(newAccbookDTO);

        // then
        assertNotNull(actualAccbook);    // 가계부 내역이 생성되었는지 확인
        assertNotNull(accbookRepository.findById(actualAccbook.getAccbookCode())); // 가계부 내역이 DB에 저장되었는지 확인
    }

    @DisplayName("가계부 수정 테스트")
    @ParameterizedTest
    @MethodSource("provideAccbook")
    void testModifyAccbook(String createdAt, String title, Long amount, YesOrNo isRegular,
                                  Integer memberCode, Integer accCategoryCode, Integer storeCode, Integer assetCode) {

        // given
        Accbook initialAccbook = new Accbook();
        initialAccbook.setCreatedAt(createdAt);
        initialAccbook.setTitle("네이버쇼핑");       // 초기값 (수정할 값과 다르게 설정)
        initialAccbook.setAmount(40000L);          // 초기값
        initialAccbook.setIsRegular(isRegular);
        initialAccbook.setMemberCode(memberCode);
        initialAccbook.setAccCategoryCode(accCategoryCode);
        initialAccbook.setStoreCode(storeCode);
        initialAccbook.setAssetCode(assetCode);

        accbookRepository.save(initialAccbook);

        AccbookDTO modifyAccbookDTO = new AccbookDTO();
        modifyAccbookDTO.setCreatedAt(createdAt);
        modifyAccbookDTO.setTitle(title);
        modifyAccbookDTO.setAmount(amount);
        modifyAccbookDTO.setIsRegular(isRegular);
        modifyAccbookDTO.setMemberCode(memberCode);
        modifyAccbookDTO.setAccCategoryCode(accCategoryCode);
        modifyAccbookDTO.setStoreCode(storeCode);
        modifyAccbookDTO.setAssetCode(assetCode);

        // when
        Accbook modifiedAccbook = accbookService.modifyAccbook(initialAccbook.getAccbookCode(), modifyAccbookDTO);

        // then
        // 수정 후 값을 검증 (modifiedAccbook, modifyAccbookDTO 비교)
        assertEquals(modifiedAccbook.getCreatedAt(), modifyAccbookDTO.getCreatedAt());
        assertEquals(modifiedAccbook.getTitle(), modifyAccbookDTO.getTitle());
        assertEquals(modifiedAccbook.getAmount(), modifyAccbookDTO.getAmount());
        assertEquals(modifiedAccbook.getIsRegular(), modifyAccbookDTO.getIsRegular());
        assertEquals(modifiedAccbook.getMemberCode(), modifyAccbookDTO.getMemberCode());
        assertEquals(modifiedAccbook.getAccCategoryCode(), modifyAccbookDTO.getAccCategoryCode());
        assertEquals(modifiedAccbook.getStoreCode(), modifyAccbookDTO.getStoreCode());
        assertEquals(modifiedAccbook.getAssetCode(), modifyAccbookDTO.getAssetCode());
    }

    @DisplayName("가계부 삭제 테스트")
    @ParameterizedTest
    @MethodSource("provideAccbook")
    void testRemoveAccbook(String createdAt, String title, Long amount, YesOrNo isRegular,
                           Integer memberCode, Integer accCategoryCode, Integer storeCode, Integer assetCode) {

        // given
        Accbook initialAccbook = new Accbook();
        initialAccbook.setCreatedAt(createdAt);
        initialAccbook.setTitle(title);
        initialAccbook.setAmount(amount);
        initialAccbook.setIsRegular(isRegular);
        initialAccbook.setMemberCode(memberCode);
        initialAccbook.setAccCategoryCode(accCategoryCode);
        initialAccbook.setStoreCode(storeCode);
        initialAccbook.setAssetCode(assetCode);

        accbookRepository.save(initialAccbook);

        int removeAccbookCode = initialAccbook.getAccbookCode();

        // when
        accbookService.removeAccbook(removeAccbookCode);

        // then
        assertFalse(accbookRepository.findById(removeAccbookCode).isPresent());
    }
}