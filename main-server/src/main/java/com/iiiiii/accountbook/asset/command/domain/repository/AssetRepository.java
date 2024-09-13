package com.iiiiii.accountbook.asset.command.domain.repository;

import com.iiiiii.accountbook.asset.command.domain.aggregate.entity.Asset;
import com.iiiiii.accountbook.common.AssetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

    List<Asset> findByMemberCodeAndCategory(Integer memberCode, AssetCategory assetCategory);
}
