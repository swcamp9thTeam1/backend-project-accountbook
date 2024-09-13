package com.iiiiii.accountbook.asset.command.application.service;

import com.iiiiii.accountbook.asset.command.domain.aggregate.dto.AssetDTO;
import com.iiiiii.accountbook.asset.command.domain.repository.AssetRepository;
import com.iiiiii.accountbook.common.AssetCategory;
import com.iiiiii.accountbook.common.YesOrNo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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

    @Autowired
    private AssetRepository assetRepository;

    private static Stream<Arguments> provideBankAssetNoAndMemberCode() {
        return Stream.of(
                Arguments.of(0, 7)
        );
    }

    @DisplayName("새로운 계좌 자산 등록 테스트")
    @ParameterizedTest
    @ValueSource(ints = 7)
    public void registBankAsset(int memberCode) {

        AssetDTO bankAsset = new AssetDTO(AssetCategory.B, "우리은행", 1000000L, memberCode);

        int newAssetCode = assetService.registAsset(bankAsset, null, memberCode);

        assertEquals(12, newAssetCode);
    }

    @DisplayName("새로운 체크카드 자산 등록 테스트")
    @ParameterizedTest
    @MethodSource("provideBankAssetNoAndMemberCode")
    public void registCardAsset(Integer bankAssetNo, int memberCode) {

        AssetDTO cardAsset = new AssetDTO(AssetCategory.C, "우리카드", 0L, memberCode, 12);

        assetService.registAsset(cardAsset, bankAssetNo, memberCode);

        assertEquals(cardAsset.getRelatedAssetCode(), assetRepository.findById(13).get().getRelatedAssetCode());
    }

    @DisplayName("보유 중인 자산 수정 테스트")
    @Test
    public void modifyAsset() {

        AssetDTO modifiedAsset = new AssetDTO(
                                10, AssetCategory.M, "용돈", 300000L, null,
                                                            YesOrNo.N, 3, null);
                                    // 자산명(name)과 현재잔액(balance) 수정

        assetService.modifyAsset(10, modifiedAsset);

        assertEquals("용돈", assetRepository.findById(10).get().getName());
    }

    @DisplayName("가계부 내역 등록 시 자산 잔액 변동 테스트")
    @ParameterizedTest
    @ValueSource(ints = 9)
    public void updateAssetByAccbook(int assetCode) {

        assetService.modifyAssetByOut(assetCode, 5000000L);
        assetService.modifyAssetByIn(assetCode, 2000000L);

        assertEquals(27000000L, assetRepository.findById(assetCode).get().getBalance());
    }

    @DisplayName("보유 중인 자산 삭제 시 자산의 삭제여부, 잔액, 결제일 변경 테스트")
    @ParameterizedTest
    @ValueSource(ints = 8)
    public void modifyAssetToDelete(int assetCode) {

        assetService.modifyAssetToDelete(assetCode);

        assertEquals(YesOrNo.Y, assetRepository.findById(assetCode).get().getIsDeleted());
        assertEquals(0L, assetRepository.findById(assetCode).get().getBalance());
        assertNull(assetRepository.findById(assetCode).get().getPaymentDate());
    }
}
