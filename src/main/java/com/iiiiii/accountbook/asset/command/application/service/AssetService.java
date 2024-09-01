package com.iiiiii.accountbook.asset.command.application.service;

import com.iiiiii.accountbook.asset.command.domain.aggregate.entity.Asset;
import com.iiiiii.accountbook.asset.command.domain.aggregate.dto.AssetDTO;
import com.iiiiii.accountbook.asset.command.domain.repository.AssetRepository;
import jakarta.persistence.EntityNotFoundException;
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

    /* 자산 수정 트랜잭션 */
    // 자산카테고리(category), 자산명(name), 현재잔액(balance), 결제일(paymentDate) 수정 가능
    @Transactional
    public void modifyAsset(Integer assetCode, AssetDTO modifiedAsset) {

        Asset myAsset = assetRepository.findById(assetCode)
                    .orElseThrow(() -> new EntityNotFoundException("해당 코드의 자산은 존재하지 않습니다."));

        if (myAsset.getCode() == modifiedAsset.getCode()) {
            assetRepository.save(modelMapper.map(modifiedAsset, Asset.class));
        } else {
            throw new IllegalArgumentException("수정 불가 - 자산 코드가 일치하지 않습니다.");
        }
    }
}
