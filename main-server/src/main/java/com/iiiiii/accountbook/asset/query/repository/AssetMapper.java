package com.iiiiii.accountbook.asset.query.repository;

import com.iiiiii.accountbook.asset.query.dto.AssetDTO;
import com.iiiiii.accountbook.common.AssetCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AssetMapper {

    List<AssetDTO> selectMyAllAssets(int memberCode);

    List<AssetDTO> selectMyAssetsByCategory(int memberCode, AssetCategory category);
}
