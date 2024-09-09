package com.iiiiii.accountbook.store.query.service;

import com.iiiiii.accountbook.common.YesOrNo;
import com.iiiiii.accountbook.store.common.StoreSearchCriteria;
import com.iiiiii.accountbook.store.query.dto.StoreDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class StoreServiceTests {

    @Autowired
    private StoreService storeService;


    private static Stream<Arguments> providerStoreCodeSource() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(10)
        );
    }

    @DisplayName("가게 ID로 가게 1개 조회 테스트")
    @ParameterizedTest
    @MethodSource("providerStoreCodeSource")
    public void testFindStoreById(int storeCode) {

        // given

        // when
        StoreDTO foundStore = storeService.findStoreById(storeCode);

        // then
        if (foundStore != null) {
            assertThat(foundStore.getStoreCode()).isEqualTo(storeCode);
        }
    }

    private static Stream<Arguments> providerStoreSearchCriteria() {
        return Stream.of(
                Arguments.of(new StoreSearchCriteria()),
                Arguments.of(new StoreSearchCriteria(YesOrNo.Y)),
                Arguments.of(new StoreSearchCriteria(YesOrNo.N)),
                Arguments.of(new StoreSearchCriteria(YesOrNo.Y, true))
        );
    }

    @DisplayName("여러 조건으로 가게 검색")
    @ParameterizedTest
    @MethodSource("providerStoreSearchCriteria")
    public void testSearchStore(StoreSearchCriteria criteria) {

        // given

        // when
        List<StoreDTO> foundStores = storeService.searchStore(criteria);

        // then
        if (!foundStores.isEmpty()) {
            if (criteria.getIsGood() != null) {
                boolean result = true;
                for (StoreDTO foundStore : foundStores) {
                    result = foundStore.getIsGood() == criteria.getIsGood();
                }
                assertThat(result).isEqualTo(true);
            }
        }
    }

    private static Stream<Arguments> providerStoreLatLng() {
        return Stream.of(
                Arguments.of(new StoreSearchCriteria("37.497436", "126.927531")),
                Arguments.of(new StoreSearchCriteria("37.496", "126.9531")),
                Arguments.of(new StoreSearchCriteria("37.4979", "127.0276"))
        );
    }

    @DisplayName("위도,경도로 가게 검색")
    @ParameterizedTest
    @MethodSource("providerStoreLatLng")
    public void testSearchStoreByLatLng(StoreSearchCriteria latLngCriteria) {

        // given

        // when
        Integer foundStoreCode = storeService.findStoreCodeByLatLng(latLngCriteria);

        // then
        if (foundStoreCode != null) {
            StoreDTO storeDTO = storeService.findStoreById(foundStoreCode);
            assertThat(storeDTO.getLatitude()).isEqualTo(latLngCriteria.getLatitude());
            assertThat(storeDTO.getLongitude()).isEqualTo(latLngCriteria.getLongitude());
        }
    }
}