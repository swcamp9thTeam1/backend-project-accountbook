package com.iiiiii.accountbook.asset.command.application.service;

import com.iiiiii.accountbook.asset.command.application.service.AssetService;
import com.iiiiii.accountbook.asset.command.domain.aggregate.dto.AssetDTO;
import com.iiiiii.accountbook.asset.command.domain.repository.AssetRepository;
import com.iiiiii.accountbook.common.AssetCategory;
import com.iiiiii.accountbook.common.YesOrNo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AssetServiceTests {

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetRepository assetRepository;

    @DisplayName("새로운 자산 등록 테스트")
    @Test
    public void registAsset() {

        AssetDTO asset = new AssetDTO(AssetCategory.B, "우리은행", 100000L, 3);

        assetService.registAsset(asset);

        assertNotNull(assetRepository);
    }

    @DisplayName("보유 중인 자산 수정 테스트")
    @Test
    public void modifyAsset() {

        AssetDTO modifiedAsset = new AssetDTO(
                10, AssetCategory.M, "용돈", 300000L, null, YesOrNo.N, 3);
                // 자산명(name)과 현재잔액(balance) 수정

        assetService.modifyAsset(10, modifiedAsset);

        assertEquals("용돈", assetRepository.findById(10).get().getName());
    }

    @DisplayName("가계부 내역 등록 시 자산 잔액 변동 테스트")
    @Test
    public void updateAssetByAccbook() {

        assertDoesNotThrow(() -> assetService.modifyAssetByOut(9, 5000000L));
        assertDoesNotThrow(() -> assetService.modifyAssetByIn(9, 2000000L));
    }

    @DisplayName("보유 중인 자산 삭제 시 자산의 삭제여부, 잔액, 결제일 변경 테스트")
    @ParameterizedTest
    @ValueSource(ints = 11)
    public void modifyAssetToDelete(int assetCode) {

        assetService.modifyAssetToDelete(assetCode);

        assertEquals(YesOrNo.Y, assetRepository.findById(assetCode).get().getIsDeleted());
        assertEquals(0L, assetRepository.findById(assetCode).get().getBalance());
        assertNull(assetRepository.findById(assetCode).get().getPaymentDate());
    }
}
