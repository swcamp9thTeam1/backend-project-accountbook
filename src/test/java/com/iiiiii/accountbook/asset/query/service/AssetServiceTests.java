package com.iiiiii.accountbook.asset.query.service;

import com.iiiiii.accountbook.asset.query.dto.AssetDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AssetServiceTests {

    @Autowired
    private AssetService assetService;

    @DisplayName("한 회원의 모든 자산과 합계 조회")
    @ParameterizedTest
    @ValueSource(ints = 2)
    public void findMyAllAssetsAndBalance(int memberCode) {
        List<AssetDTO> assets = assetService.findMyAllAssets(memberCode);
        int totalBalance = assetService.calcTotalBalance(assets, memberCode);
        Assertions.assertEquals(6000000, totalBalance);
    }
}
