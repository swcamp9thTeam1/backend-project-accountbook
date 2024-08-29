package com.iiiiii.accountbook.asset.command.service;

import com.iiiiii.accountbook.asset.command.dto.AssetDTO;
import com.iiiiii.accountbook.asset.command.repository.AssetRepository;
import com.iiiiii.accountbook.common.AssetCategory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AssetServiceTests {

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetRepository assetRepository;

    @DisplayName("새로운 자산 등록 테스트")
    @Test
    public void registAsset() {

        AssetDTO asset = new AssetDTO(AssetCategory.B, "우리은행", 100000L, 1);

        assetService.registAsset(asset);

        Assertions.assertNotNull(assetRepository);
    }
}
