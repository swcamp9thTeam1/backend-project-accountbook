package com.iiiiii.accountbook.asset.query.service;

import com.iiiiii.accountbook.asset.query.dto.AssetDTO;
import com.iiiiii.accountbook.asset.query.repository.AssetMapper;
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
    public int calcTotalBalance(List<AssetDTO> assetList, int memberCode) {
        int totalBalance = 0;

        assetList = assetMapper.selectMyAllAssets(memberCode);

        for (int i = 0; i < assetList.size(); i++) {
            totalBalance += assetList.get(i).getBalance();
        }

        return totalBalance;
    }
}
