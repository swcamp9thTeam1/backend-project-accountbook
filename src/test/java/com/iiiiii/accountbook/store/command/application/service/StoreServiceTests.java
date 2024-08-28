package com.iiiiii.accountbook.store.command.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreServiceTests {

    @Autowired
    private StoreService storeService;

    @DisplayName("착한가격업소 등록 테스트 (엑셀 파일 사용)")
    @Test
    public void testRegisterGoodStore() {

        // given
        MockMultipartFile mockMultipartFile = null;

        String fileName = "bsshList_20240827123500";
        String contentType = "xls";
        String filePath = "src/test/resources/bsshList_20240827123500.xls";

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            mockMultipartFile = new MockMultipartFile(
                    fileName,
                    fileName + "." + contentType,
                    contentType,
                    fileInputStream
            );
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        // when

        // then
        MockMultipartFile finalMockMultipartFile = mockMultipartFile;
        assertNotNull(finalMockMultipartFile);
        assertDoesNotThrow(() -> storeService.registerGoodStore(finalMockMultipartFile));
    }

}