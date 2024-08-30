package com.iiiiii.accountbook.asset.command.repository;

import com.iiiiii.accountbook.asset.command.aggregate.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Integer> {
}
