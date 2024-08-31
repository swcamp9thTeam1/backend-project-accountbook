package com.iiiiii.accountbook.asset.query.service;

import com.iiiiii.accountbook.common.AssetCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AssetServiceTests {

    @Autowired
    private AssetService assetService;

    private static Stream<Arguments> provideMemberCodeAndCategory() {
        return Stream.of(
                Arguments.of(1, AssetCategory.B)
        );
    }

    @DisplayName("한 회원의 모든 자산과 잔액 합계 조회 테스트")
    @ParameterizedTest
    @ValueSource(ints = 2)
    public void findMyAllAssetsAndBalance(int memberCode) {
        int totalBalance = assetService.calcTotalBalance(memberCode);
        assertEquals(6000000, totalBalance);
    }

    @DisplayName("한 회원의 카테고리별 자산과 잔액 합계 조회 테스트")
    @ParameterizedTest
    @MethodSource("provideMemberCodeAndCategory")
    public void findMyAssetsAndBalance(int memberCode, AssetCategory category) {
        int totalBalance = assetService.calcTotalBalanceByCategory(memberCode, category);
        assertEquals(25000000, totalBalance);
    }
}
