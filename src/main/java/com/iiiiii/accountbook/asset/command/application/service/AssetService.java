package com.iiiiii.accountbook.asset.command.application.service;

import com.iiiiii.accountbook.asset.command.domain.aggregate.entity.Asset;
import com.iiiiii.accountbook.asset.command.domain.aggregate.dto.AssetDTO;
import com.iiiiii.accountbook.asset.command.domain.repository.AssetRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AssetServiceCommand")
@Slf4j
public class AssetService {

    private final ModelMapper modelMapper;
    private final AssetRepository assetRepository;

    @Autowired
    public AssetService(ModelMapper modelMapper, AssetRepository assetRepository) {
        this.modelMapper = modelMapper;
        this.assetRepository = assetRepository;
    }

    /* 자산 등록 트랜잭션 */
    @Transactional
    public void registAsset(AssetDTO newAsset) {
        assetRepository.save(modelMapper.map(newAsset, Asset.class));
    }
}
