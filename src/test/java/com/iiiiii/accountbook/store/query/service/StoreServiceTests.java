package com.iiiiii.accountbook.store.query.service;

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

    @DisplayName("가게 ID로 가게 1개 조회 테스트")
    @ParameterizedTest
    @MethodSource("storeCodeSource")
    public void testFindStoreById(int storeCode) {
        assertDoesNotThrow(() -> storeService.findStoreById(storeCode));
    }
}