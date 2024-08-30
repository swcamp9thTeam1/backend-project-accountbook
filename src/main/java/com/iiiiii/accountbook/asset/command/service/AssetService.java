package com.iiiiii.accountbook.asset.command.service;

import com.iiiiii.accountbook.asset.command.aggregate.entity.Asset;
import com.iiiiii.accountbook.asset.command.dto.AssetDTO;
import com.iiiiii.accountbook.asset.command.repository.AssetRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        assetRepository.save(modelMapper.map(newAsset, Asset.class));
    }
}
