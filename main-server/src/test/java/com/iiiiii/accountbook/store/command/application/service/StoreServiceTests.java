package com.iiiiii.accountbook.store.command.application.service;

import com.iiiiii.accountbook.common.YesOrNo;
import com.iiiiii.accountbook.exception.NotAllowedRegisterGoodStoreFileTypeException;
import com.iiiiii.accountbook.store.command.domain.aggregate.entity.Store;
import com.iiiiii.accountbook.store.command.domain.aggregate.vo.RegisterStoreVO;
import com.iiiiii.accountbook.store.command.domain.repository.StoreRepository;
import com.iiiiii.accountbook.store.query.dto.StoreDTO;
import com.iiiiii.accountbook.store.query.repository.StoreMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreServiceTests {

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private StoreRepository storeRepository;

    private static Stream<Arguments> providerStoreFiles() {
        return Stream.of(
                Arguments.of("bsshList_20240827123500", "xls"),
                Arguments.of("images", "jpeg")
        );
    }

    @DisplayName("착한가격업소 등록 테스트 (엑셀 파일 사용)")
    @ParameterizedTest
    @MethodSource("providerStoreFiles")
    public void testRegisterGoodStore(String fileName, String ext) {

        // given
        MockMultipartFile mockMultipartFile = null;

        String originalFileName = fileName + "." + ext;
        String filePath = "src/test/resources/" + originalFileName;

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            mockMultipartFile = new MockMultipartFile(
                    fileName,
                    originalFileName,
                    ext,
                    fileInputStream
            );
        } catch (IOException e) {
            e.printStackTrace();
            fail();             // 이 단계에서 예외가 발생한 경우에는 테스트도 fail
        }

        // when

        // then
        MockMultipartFile finalMockMultipartFile = mockMultipartFile;
        if ("xls".equals(ext) || "xlsx".equals(ext)) {
            assertDoesNotThrow(() -> storeService.registerGoodStore(finalMockMultipartFile));
        } else {                // 다른 확장자의 파일을 올린 경우에는 예외가 발생하는게 맞음
            assertThrows(NotAllowedRegisterGoodStoreFileTypeException.class,
                    () -> storeService.registerGoodStore(finalMockMultipartFile));
        }
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
        int addedStoreCode = storeService.registerStore(new RegisterStoreVO(
                storeName, storeAddress, lat, lng
        ));

        // then
        StoreDTO foundStore = storeMapper.selectStoreById(addedStoreCode);
        assertThat(foundStore).isNotNull();
        assertThat(foundStore.getStoreName()).isEqualTo(storeName);
        assertThat(foundStore.getAddress()).isEqualTo(storeAddress);
        assertThat(foundStore.getLatitude()).isEqualTo(lat);
        assertThat(foundStore.getLongitude()).isEqualTo(lng);
    }

    @DisplayName("착한가격업소였던 가게를 일반가게로 변경하는 테스트")
    @Test
    public void testModifyGoodStoreToN() throws Exception {

        // given
        // 기존 목록에서 착한가게만 모두 불러오기
        List<Store> foundGoodStores = storeRepository.findByIsGood(YesOrNo.Y);

        // when
        for (Store foundGoodStore : foundGoodStores) {
            storeService.modifyGoodStoreToN(foundGoodStore.getStoreCode());
        }

        // then
        List<Store> reFoundGoodStores = storeRepository.findByIsGood(YesOrNo.Y);
        assertThat(reFoundGoodStores.size()).isEqualTo(0);
    }

}