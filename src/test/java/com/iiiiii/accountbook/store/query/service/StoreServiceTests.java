package com.iiiiii.accountbook.store.query.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreServiceTests {

    @Autowired
    private StoreService storeService;

    @Test
    @DisplayName("가게 조회 테스트")
    public void testFindAllStores() {
        assertDoesNotThrow(() -> storeService.findAllStores());
    }
}