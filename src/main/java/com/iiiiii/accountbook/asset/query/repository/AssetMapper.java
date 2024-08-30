package com.iiiiii.accountbook.asset.query.repository;

import com.iiiiii.accountbook.asset.query.dto.AssetDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssetMapper {

    List<AssetDTO> selectMyAllAssets(int memberCode);
}
