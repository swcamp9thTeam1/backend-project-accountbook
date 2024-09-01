package com.iiiiii.accountbook.asset.command.application.service;

import com.iiiiii.accountbook.asset.command.domain.aggregate.entity.Asset;
import com.iiiiii.accountbook.asset.command.domain.aggregate.dto.AssetDTO;
import com.iiiiii.accountbook.asset.command.domain.repository.AssetRepository;
import com.iiiiii.accountbook.common.YesOrNo;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Conditions;
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
        this.modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());   // null인 부분은 매핑 제외
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

        if (myAsset.getCode() == modifiedAsset.getCode() && myAsset.getIsDeleted() == YesOrNo.N) {
            assetRepository.save(modelMapper.map(modifiedAsset, Asset.class));
        } else if (myAsset.getCode() != modifiedAsset.getCode()) {
            throw new IllegalArgumentException("자산 코드가 일치하지 않습니다.");
        } else {
            throw new IllegalArgumentException("삭제된 자산입니다.");
        }
    }

    /* 가계부 지출 내역 등록 시 자산 잔액 수정 트랜잭션 */
    // 가계부 내역의 금액(amount)와 자산의 자산 코드(code) 사용
    @Transactional
    public void modifyAssetByOut(Integer assetCode, Long amount) {

        Asset usedAsset = assetRepository.findById(assetCode)
                .orElseThrow(() -> new EntityNotFoundException("해당 코드의 자산은 존재하지 않습니다."));

        if (usedAsset.getBalance() >= amount && usedAsset.getIsDeleted() == YesOrNo.N) {
            usedAsset.setBalance(usedAsset.getBalance() - amount);
        } else if (usedAsset.getIsDeleted() != YesOrNo.N) {
            throw new IllegalArgumentException("삭제된 자산입니다.");
        } else {
            throw new IllegalArgumentException("해당 자산의 현재 잔액이 입력한 금액보다 작습니다.");
        }

        assetRepository.save(usedAsset);
    }

    /* 가계부 입금 내역 등록 시 자산 잔액 수정 트랜잭션 */
    // 가계부 내역의 금액(amount)와 자산의 자산 코드(code) 사용
    @Transactional
    public void modifyAssetByIn(Integer assetCode, Long amount) {

        Asset increasedAsset = assetRepository.findById(assetCode)
                .orElseThrow(() -> new EntityNotFoundException("해당 코드의 자산은 존재하지 않습니다."));

        if (increasedAsset.getIsDeleted() == YesOrNo.N) {
            increasedAsset.setBalance(increasedAsset.getBalance() + amount);
        } else {
            throw new IllegalArgumentException("삭제된 자산입니다.");
        }

        assetRepository.save(increasedAsset);
    }

    /* 자산 삭제 트랜잭션 */
    // soft delete (삭제여부 -> Y, 잔액 -> 0, 결제일 -> null)
    @Transactional
    public void modifyAssetToDelete(Integer assetCode) {

        Asset myAsset = assetRepository.findById(assetCode)
                .orElseThrow(() -> new EntityNotFoundException("해당 코드의 자산은 존재하지 않습니다."));

        if (myAsset.getIsDeleted() != YesOrNo.N)
            throw new IllegalArgumentException("이미 삭제된 자산입니다.");

        myAsset.setIsDeleted(YesOrNo.Y);
        myAsset.setBalance(0L);
        myAsset.setPaymentDate(null);

        assetRepository.save(myAsset);
    }
}
