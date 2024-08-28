package com.iiiiii.accountbook.store.command.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreServiceTests {

    @Autowired
    private StoreService storeService;

    private static Stream<Arguments> goodStoreFile() {
        return Stream.of(
                Arguments.of(new File("src/test/resources/bsshList_20240827123500.xls"))
        );
    }

    @DisplayName("착한가격업소 등록 테스트 (엑셀 파일 사용)")
    @ParameterizedTest
    @MethodSource("goodStoreFile")
    public void testRegisterGoodStore(File goodStoreFile) {

        // given
        MockMultipartFile mockMultipartFile = null;

        String fileName = "bsshList_20240827123500";
        String contentType = "xls";
        String filePath = "src/test/resources/excel/bsshList_20240827123500.xls";

        try (FileInputStream fileInputStream = new FileInputStream(goodStoreFile)) {
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