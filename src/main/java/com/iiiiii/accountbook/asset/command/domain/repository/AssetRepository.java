package com.iiiiii.accountbook.asset.command.domain.repository;

import com.iiiiii.accountbook.asset.command.domain.aggregate.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Integer> {
}
