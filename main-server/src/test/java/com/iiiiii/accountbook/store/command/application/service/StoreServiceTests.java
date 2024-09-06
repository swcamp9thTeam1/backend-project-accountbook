package com.iiiiii.accountbook.store.command.application.service;

import com.iiiiii.accountbook.store.command.domain.aggregate.entity.Store;
import com.iiiiii.accountbook.store.command.domain.aggregate.vo.RegisterStoreVO;
import com.iiiiii.accountbook.store.command.domain.repository.StoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreServiceTests {

    private static final Logger log = LoggerFactory.getLogger(StoreServiceTests.class);

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

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

    @DisplayName("일반가게 1개 등록 테스트")
    @Test
    public void testRegisterStore() {

        // given
        String storeName = "동화자앙";
        String storeAddress = "서울 동작구 여의대방로24길 137";
        String lat = "37.499300";
        String lng = "126.927739";

        // when
        storeService.registerStore(new RegisterStoreVO(
                storeName, storeAddress, lat, lng
        ));

        // then
//        List<Store> foundStores = storeRepository.findByLatitudeAndLongitude(lat, lng);
//        foundStores.forEach(store -> {
//            assertEquals(store.getStoreName(),storeName);
//        });

    }

    @DisplayName("착한가격업소였던 가게를 일반가게로 변경하는 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3})
    public void testModifyGoodStoreToN(int storeCode) {

        // given

        // when

        // then
        assertDoesNotThrow(() -> storeService.modifyGoodStoreToN(storeCode));
    }

}