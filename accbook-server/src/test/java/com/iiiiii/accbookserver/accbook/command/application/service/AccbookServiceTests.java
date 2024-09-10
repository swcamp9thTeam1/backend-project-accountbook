package com.iiiiii.accbookserver.accbook.command.application.service;

import com.iiiiii.accbookserver.accbook.client.AccCategoryServiceClient;
import com.iiiiii.accbookserver.accbook.client.AssetServiceClient;
import com.iiiiii.accbookserver.accbook.client.StoreServiceClient;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.dto.AccbookDTO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.dto.RegistStoreDTO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.dto.RequestRegistAccbookDTO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.entity.Accbook;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.RequestRegistStoreVO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.ResponseAccCategoryVO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.ResponseStoreCodeVO;
import com.iiiiii.accbookserver.accbook.command.domain.repository.AccbookRepository;
import com.iiiiii.accbookserver.common.InOrOut;
import com.iiiiii.accbookserver.common.InOrOutOrTransfer;
import com.iiiiii.accbookserver.common.YesOrNo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class AccbookServiceTests {
    @InjectMocks
    private AccbookService accbookService;

    @Mock
    private AccbookRepository accbookRepository;

    @Mock
    private StoreServiceClient storeServiceClient;

    @Mock
    private AccCategoryServiceClient accCategoryServiceClient;

    @Mock
    private AssetServiceClient assetServiceClient;

    @BeforeEach
    void setUp() { // Mock 객체 초기화
        MockitoAnnotations.openMocks(this);
    }

    private static Stream<Arguments> provideAccbook() {
        return Stream.of(
                Arguments.of( "2024-08-29", "쿠팡", 58000L, YesOrNo.N, 1, 1, null, 1),
                Arguments.of( "2024-08-30", "웨이브", 10000L, YesOrNo.Y, 1, 2, null, 2)
        );
    }

    /*  Mockito를 통한 Service & Repository 테스트 요약
     *  해당 테스트를 위해서 Service 구현체와 모의 객체를 구성한 Mapper 간의 비교
     *  가상의 모의 객체의 결과와 Service의 구현체에서 구성한 값이 같으면 통과
    * */
//    @Test
//    @DisplayName("방문한 가게가 없을 때 가계부 등록 테스트")
//    void registAccbookTests_noVisitedStore() {
//        // given
//        RequestRegistAccbookDTO newAccbook = new RequestRegistAccbookDTO("2024-09-01", "test", 1000L, YesOrNo.N, 1, 1, 1, null);
//
//        ResponseStoreCodeVO mockResponseStoreCodeVO = new ResponseStoreCodeVO();
//        Map<String, Object> result = new HashMap<>();
//        result.put("storeCode", 10);
//        mockResponseStoreCodeVO.setResult(result);
//        Mockito.when(storeServiceClient.getStoreCodeByLatLng(any(String.class), any(String.class)))
//                .thenReturn(mockResponseStoreCodeVO);
//
//        // AccCategoryServiceClient Mock 설정
//        ResponseAccCategoryVO mockResponseAccCategoryVO = new ResponseAccCategoryVO(1, );
//        mockResponseAccCategoryVO.getFinanceType(InOrOut.I);
//
//
//        // when
//
//        // then
//    }
    @Test
    @Transactional
    @DisplayName("방문한 가게가 없을 때 가계부 등록 테스트")
    void registAccbookTests_noVisitedStore() {
        // given
        RequestRegistAccbookDTO initialAccbook = new RequestRegistAccbookDTO();
        initialAccbook.setRegistStoreDTO(null);         // 방문한 가게를 입력하지 않은 경우
        initialAccbook.setCreatedAt("2024-09-01");
        initialAccbook.setTitle("아메리카노");
        initialAccbook.setAmount(3000L);
        initialAccbook.setIsRegular(YesOrNo.N);
        initialAccbook.setMemberCode(1);
        initialAccbook.setAccCategoryCode(1);
        initialAccbook.setAssetCode(1);

        // ServiceClient 호출 (FeignClient 통신)
        ResponseAccCategoryVO responseAccCategoryVO = new ResponseAccCategoryVO();
        responseAccCategoryVO.setFinanceType(InOrOutOrTransfer.I);
        when(accCategoryServiceClient.findOneAccCategory(initialAccbook.getAccCategoryCode())).thenReturn(responseAccCategoryVO);
        doNothing().when(assetServiceClient).modifyAssetByIn(1, 3000L);

        // expected
        Accbook expectedAccbook = new Accbook();
        expectedAccbook.setStoreCode(null);             // 방문한 가게를 입력하지 않은 경우
        expectedAccbook.setCreatedAt(initialAccbook.getCreatedAt());
        expectedAccbook.setTitle(initialAccbook.getTitle());
        expectedAccbook.setAmount(initialAccbook.getAmount());
        expectedAccbook.setIsRegular(initialAccbook.getIsRegular());
        expectedAccbook.setMemberCode(initialAccbook.getMemberCode());
        expectedAccbook.setAccCategoryCode(initialAccbook.getAccCategoryCode());
        expectedAccbook.setAssetCode(initialAccbook.getAssetCode());

        // accbookRepository.save() 실행 시 expectedAccbook 리턴
        when(accbookRepository.save(any(Accbook.class))).thenReturn(expectedAccbook);

        // when
        // actual
        Accbook actualAccbook  = accbookService.registAccbook(initialAccbook);

        // then
        assertNotNull(actualAccbook);
        assertEquals(expectedAccbook.getTitle(), actualAccbook.getTitle());
        assertEquals(expectedAccbook.getAmount(), actualAccbook.getAmount());
        assertEquals(expectedAccbook.getIsRegular(), actualAccbook.getIsRegular());
        assertEquals(expectedAccbook.getMemberCode(), actualAccbook.getMemberCode());
        assertEquals(expectedAccbook.getAccCategoryCode(), actualAccbook.getAccCategoryCode());
        assertEquals(expectedAccbook.getAssetCode(), actualAccbook.getAssetCode());
    }


    @Test
    @DisplayName("방문한 가게가 있을 때 가계부 등록 테스트")
    void registAccbookTests_DBExistStore() {
        // given
        RegistStoreDTO existStore = new RegistStoreDTO("스타벅스", "서울특별시 동작구 스타벅스로 999", "40.9090", "127.8912");
        RequestRegistAccbookDTO initialAccbook = new RequestRegistAccbookDTO();
        initialAccbook.setRegistStoreDTO(existStore);
        initialAccbook.setCreatedAt("2024-09-01");
        initialAccbook.setTitle("아메리카노");
        initialAccbook.setAmount(3000L);
        initialAccbook.setIsRegular(YesOrNo.N);
        initialAccbook.setMemberCode(1);
        initialAccbook.setAccCategoryCode(1);
        initialAccbook.setAssetCode(1);

        // ServiceClient 호출 (FeignClient 통신)
        Map<String, Object> result = new HashMap<>();
        result.put("storeCode", 1);
        ResponseStoreCodeVO responseStoreCodeVO = new ResponseStoreCodeVO();
        responseStoreCodeVO.setResult(result); // result 맵을 ResponseStoreCodeVO에 설정
        ResponseAccCategoryVO responseAccCategoryVO = new ResponseAccCategoryVO();
        responseAccCategoryVO.setFinanceType(InOrOutOrTransfer.I);

        when(storeServiceClient.getStoreCodeByLatLng(existStore.getLatitude(), existStore.getLongitude())).thenReturn(responseStoreCodeVO);
        when(accCategoryServiceClient.findOneAccCategory(initialAccbook.getAccCategoryCode())).thenReturn(responseAccCategoryVO);
        doNothing().when(assetServiceClient).modifyAssetByIn(1, 3000L);

        // expected
        Accbook expectedAccbook = new Accbook();
        expectedAccbook.setStoreCode(1);
        expectedAccbook.setCreatedAt(initialAccbook.getCreatedAt());
        expectedAccbook.setTitle(initialAccbook.getTitle());
        expectedAccbook.setAmount(initialAccbook.getAmount());
        expectedAccbook.setIsRegular(initialAccbook.getIsRegular());
        expectedAccbook.setMemberCode(initialAccbook.getMemberCode());
        expectedAccbook.setAccCategoryCode(initialAccbook.getAccCategoryCode());
        expectedAccbook.setAssetCode(initialAccbook.getAssetCode());

        // accbookRepository.save() 실행 시 expectedAccbook 리턴
        when(accbookRepository.save(any(Accbook.class))).thenReturn(expectedAccbook);

        // when
        // actual
        Accbook actualAccbook  = accbookService.registAccbook(initialAccbook);

        // then
        assertNotNull(actualAccbook);
        assertEquals(expectedAccbook.getTitle(), actualAccbook.getTitle());
        assertEquals(expectedAccbook.getAmount(), actualAccbook.getAmount());
        assertEquals(expectedAccbook.getIsRegular(), actualAccbook.getIsRegular());
        assertEquals(expectedAccbook.getMemberCode(), actualAccbook.getMemberCode());
        assertEquals(expectedAccbook.getAccCategoryCode(), actualAccbook.getAccCategoryCode());
        assertEquals(expectedAccbook.getAssetCode(), actualAccbook.getAssetCode());
    }

//    @DisplayName("가계부 수정 테스트")
//    @ParameterizedTest
//    @MethodSource("provideAccbook")
//    void testModifyAccbook(String createdAt, String title, Long amount, YesOrNo isRegular,
//                           Integer memberCode, Integer accCategoryCode, Integer storeCode, Integer assetCode) {
//
//        // given
//        Accbook initialAccbook = new Accbook();
//        initialAccbook.setCreatedAt(createdAt);
//        initialAccbook.setTitle("네이버쇼핑");       // 초기값 (수정할 값과 다르게 설정)
//        initialAccbook.setAmount(40000L);          // 초기값
//        initialAccbook.setIsRegular(isRegular);
//        initialAccbook.setMemberCode(memberCode);
//        initialAccbook.setAccCategoryCode(accCategoryCode);
//        initialAccbook.setStoreCode(storeCode);
//        initialAccbook.setAssetCode(assetCode);
//
//        accbookRepository.save(initialAccbook);
//
//        RequestRegistAccbookDTO modifyAccbookDTO = new RequestRegistAccbookDTO();
//        modifyAccbookDTO.setCreatedAt(createdAt);
//        modifyAccbookDTO.setTitle(title);
//        modifyAccbookDTO.setAmount(amount);
//        modifyAccbookDTO.setIsRegular(isRegular);
//        modifyAccbookDTO.setMemberCode(memberCode);
//        modifyAccbookDTO.setAccCategoryCode(accCategoryCode);
//        modifyAccbookDTO.setAssetCode(assetCode);
//
//        // when
//        Accbook modifiedAccbook = accbookService.modifyAccbook(initialAccbook.getAccbookCode(), modifyAccbookDTO);
//
//        // then
//        // 수정 후 값을 검증 (modifiedAccbook, modifyAccbookDTO 비교)
//        assertEquals(modifiedAccbook.getCreatedAt(), modifyAccbookDTO.getCreatedAt());
//        assertEquals(modifiedAccbook.getTitle(), modifyAccbookDTO.getTitle());
//        assertEquals(modifiedAccbook.getAmount(), modifyAccbookDTO.getAmount());
//        assertEquals(modifiedAccbook.getIsRegular(), modifyAccbookDTO.getIsRegular());
//        assertEquals(modifiedAccbook.getMemberCode(), modifyAccbookDTO.getMemberCode());
//        assertEquals(modifiedAccbook.getAccCategoryCode(), modifyAccbookDTO.getAccCategoryCode());
//        assertEquals(modifiedAccbook.getAssetCode(), modifyAccbookDTO.getAssetCode());
//    }

//    @DisplayName("자산 카테고리가 변경된 경우 가계부 수정 테스트")
//    @Test
//    void testModifyAccbook() {
//        // given
//        // 기존 Accbook 객체 (수입 카테고리)
//        Accbook existingAccbook = new Accbook();
//        existingAccbook.setStoreCode(1);
//        existingAccbook.setCreatedAt("2024-09-01");
//        existingAccbook.setTitle("아메리카노");
//        existingAccbook.setAmount(3000L);
//        existingAccbook.setIsRegular(YesOrNo.N);
//        existingAccbook.setMemberCode(1);
//        existingAccbook.setAccCategoryCode(1);  // 수입 카테고리
//        existingAccbook.setAssetCode(1);
//
//        // 수정할 가계부 정보를 담은 DTO
//        RequestRegistAccbookDTO modifyAccbook = new RequestRegistAccbookDTO();
//        modifyAccbook.setCreatedAt("2024-09-01");
//        modifyAccbook.setTitle("아메리카노");
//        modifyAccbook.setAmount(3000L);
//        modifyAccbook.setIsRegular(YesOrNo.N);
//        modifyAccbook.setMemberCode(1);
//        modifyAccbook.setAccCategoryCode(2); // 지출 카테고리
//        modifyAccbook.setAssetCode(1);
//
//        // 기존 가계부 데이터 반환 (Mock)
//        when(accbookRepository.findById(1)).thenReturn(Optional.of(existingAccbook));
//
//        // 기존 수입 카테고리 ServiceClient 응답
//        ResponseAccCategoryVO responseAccCategoryVO = new ResponseAccCategoryVO();
//        responseAccCategoryVO.setFinanceType(InOrOut.I); // 수입
//        when(accCategoryServiceClient.findOneAccCategory(existingAccbook.getAccCategoryCode()))
//                .thenReturn(responseAccCategoryVO);
//        doNothing().when(assetServiceClient).modifyAssetByIn(1, 3000L); // 수입 시 자산 변경
//
//        // 지출 카테고리 ServiceClient 응답
//        ResponseAccCategoryVO modifiedResponseAccCategoryVO = new ResponseAccCategoryVO();
//        modifiedResponseAccCategoryVO.setFinanceType(InOrOut.O); // 지출
//        when(accCategoryServiceClient.findOneAccCategory(modifyAccbook.getAccCategoryCode()))
//                .thenReturn(modifiedResponseAccCategoryVO);
//        doNothing().when(assetServiceClient).modifyAssetByOut(1, 3000L); // 지출 시 자산 변경
//
//        // 수정된 가계부 예상 값
//        Accbook modifiedExpectedAccbook = new Accbook();
//        modifiedExpectedAccbook.setStoreCode(1);
//        modifiedExpectedAccbook.setCreatedAt(modifyAccbook.getCreatedAt());
//        modifiedExpectedAccbook.setTitle(modifyAccbook.getTitle());
//        modifiedExpectedAccbook.setAmount(modifyAccbook.getAmount());
//        modifiedExpectedAccbook.setIsRegular(modifyAccbook.getIsRegular());
//        modifiedExpectedAccbook.setMemberCode(modifyAccbook.getMemberCode());
//        modifiedExpectedAccbook.setAccCategoryCode(modifyAccbook.getAccCategoryCode());
//        modifiedExpectedAccbook.setAssetCode(modifyAccbook.getAssetCode());
//
//        // accbookRepository.save() 실행 시 수정된 가계부 리턴
//        when(accbookRepository.save(any(Accbook.class))).thenReturn(modifiedExpectedAccbook);
//
//        // when
//        Accbook actualAccbook = accbookService.modifyAccbook(1, modifyAccbook);
//
//        // then
//        // 수정된 가계부 확인
//        assertNotNull(actualAccbook);
//        assertEquals(modifiedExpectedAccbook.getTitle(), actualAccbook.getTitle());
//        assertEquals(modifiedExpectedAccbook.getAmount(), actualAccbook.getAmount());
//        assertEquals(modifiedExpectedAccbook.getIsRegular(), actualAccbook.getIsRegular());
//        assertEquals(modifiedExpectedAccbook.getMemberCode(), actualAccbook.getMemberCode());
//        assertEquals(modifiedExpectedAccbook.getAccCategoryCode(), actualAccbook.getAccCategoryCode());
//        assertEquals(modifiedExpectedAccbook.getAssetCode(), actualAccbook.getAssetCode());
//    }

//    @DisplayName("가계부 삭제 테스트")
//    @ParameterizedTest
//    @MethodSource("provideAccbook")
//    void testRemoveAccbook(String createdAt, String title, Long amount, YesOrNo isRegular,
//                           Integer memberCode, Integer accCategoryCode, Integer storeCode, Integer assetCode) {
//
//        // given
//        Accbook initialAccbook = new Accbook();
//        initialAccbook.setCreatedAt(createdAt);
//        initialAccbook.setTitle(title);
//        initialAccbook.setAmount(amount);
//        initialAccbook.setIsRegular(isRegular);
//        initialAccbook.setMemberCode(memberCode);
//        initialAccbook.setAccCategoryCode(accCategoryCode);
//        initialAccbook.setStoreCode(storeCode);
//        initialAccbook.setAssetCode(assetCode);
//
//        accbookRepository.save(initialAccbook);
//
//        int removeAccbookCode = initialAccbook.getAccbookCode();
//
//        // when
//        accbookService.removeAccbook(removeAccbookCode);
//
//        // then
//        assertFalse(accbookRepository.findById(removeAccbookCode).isPresent());
//    }
}