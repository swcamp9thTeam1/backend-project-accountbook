package com.iiiiii.accountbook.accbook.command.application.service;;

import com.iiiiii.accountbook.accbook.command.domain.aggregate.dto.AccbookDTO;
import com.iiiiii.accountbook.accbook.command.domain.aggregate.entity.Accbook;
import com.iiiiii.accountbook.accbook.command.domain.repository.AccbookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("AccbookServiceCommand")
@Slf4j
public class AccbookService {

    private final AccbookRepository accbookRepository;

    public AccbookService(AccbookRepository accbookRepository) {
        this.accbookRepository = accbookRepository;
    }

    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Accbook registAccbook(AccbookDTO newAccbook) {

        // 1. 새로 등록한 가계부 추출 (DTO에서)
        Accbook accbook = new Accbook();
        accbook.setCreatedAt(newAccbook.getCreatedAt());
        accbook.setTitle(newAccbook.getTitle());
        accbook.setAmount(newAccbook.getAmount());
        accbook.setIsRegular(newAccbook.getIsRegular());
        accbook.setMemberCode(newAccbook.getMemberCode());
        accbook.setAccCategoryCode(newAccbook.getAccCategoryCode());
        accbook.setStoreCode(newAccbook.getStoreCode());
        accbook.setAssetCode(newAccbook.getAssetCode());

        // TODO. 2. 자산 변경
        // assetCode, amount를 이용해서 해당 자산의 잔액을 갱신해야 함

        // TODO. 3. store DB에 방문한 가게등록 (store DB에 저장되어있지 않은 경우)
        // storeCode로 store DB를 조회 -> 가게가 존재하는 경우 -> 확인 끝
        //                              가게가 존재하지 않는 경우 -> store DB에 등록

        accbookRepository.save(accbook);
        return accbook;
    }
}
