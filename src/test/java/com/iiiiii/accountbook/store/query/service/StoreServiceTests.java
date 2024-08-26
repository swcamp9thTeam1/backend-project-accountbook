package com.iiiiii.accountbook.store.query.service;

import com.iiiiii.accountbook.common.YesOrNo;
import com.iiiiii.accountbook.store.common.StoreSearchCriteria;
import com.iiiiii.accountbook.store.query.dto.StoreDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreServiceTests {

    @Autowired
    private StoreService storeService;

    @DisplayName("가게 조회 테스트")
    @Test
    public void testFindAllStores() {
        assertDoesNotThrow(() -> storeService.findAllStores());
    }

    private static Stream<Arguments> storeCodeSource() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(10)
        );
    }

    private static Stream<Arguments> storeSearchCriteria() {
        return Stream.of(
                Arguments.of(new StoreSearchCriteria()),
                Arguments.of(new StoreSearchCriteria(false)),
                Arguments.of(new StoreSearchCriteria(true))
        );
    }

    @DisplayName("가게 ID로 가게 1개 조회 테스트")
    @ParameterizedTest
    @MethodSource("storeCodeSource")
    public void testFindStoreById(int storeCode) {
        assertDoesNotThrow(() -> storeService.findStoreById(storeCode));
    }

    @DisplayName("여러 조건으로 가게 검색")
    @ParameterizedTest
    @MethodSource("storeSearchCriteria")
    public void testSearchStore(StoreSearchCriteria criteria) {
        List<StoreDTO> foundStores = storeService.searchStore(criteria);

        if (!foundStores.isEmpty()) {   // 결과 목록이 비어있지 않을 때
            if (criteria.getGood()) {   // 착한가격업소를 조회하는 경우
                assertEquals(YesOrNo.Y, foundStores.get(0).getIsGood());
            }
        }
    }
}