package com.iiiiii.accountbook.asset.query.service;

import com.iiiiii.accountbook.asset.query.dto.AssetDTO;
import com.iiiiii.accountbook.asset.query.repository.AssetMapper;
import com.iiiiii.accountbook.common.AssetCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetMapper assetMapper;

    /* 한 회원의 모든 자산 조회 */
    public List<AssetDTO> findMyAllAssets(int memberCode) {

        List<AssetDTO> assetList = assetMapper.selectMyAllAssets(memberCode);

        return assetList;
    }

    /* 한 회원의 모든 자산의 잔액 합계 조회 */
    public int calcTotalBalance(int memberCode) {
        int totalBalance = 0;

        List<AssetDTO> assetList = assetMapper.selectMyAllAssets(memberCode);

        for (int i = 0; i < assetList.size(); i++) {
            totalBalance += assetList.get(i).getBalance();
        }

        return totalBalance;
    }

    /* 한 회원의 카테고리별 자산 조회 */
    public List<AssetDTO> findAssetsByCategory(int memberCode, AssetCategory category) {

        List<AssetDTO> assetList = assetMapper.selectMyAssetsByCategory(memberCode, category);

        return assetList;
    }

    /* 한 회원의 카테고리별 자산의 잔액 합계 조회 */
    public int calcTotalBalanceByCategory(int memberCode, AssetCategory category) {
        int totalBalance = 0;

        List<AssetDTO> assetList = assetMapper.selectMyAssetsByCategory(memberCode, category);

        for (int i = 0; i < assetList.size(); i++) {
            totalBalance += assetList.get(i).getBalance();
        }

        return totalBalance;
    }
}
