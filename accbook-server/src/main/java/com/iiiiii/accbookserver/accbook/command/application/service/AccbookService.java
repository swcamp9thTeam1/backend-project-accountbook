package com.iiiiii.accbookserver.accbook.command.application.service;;

import com.iiiiii.accbookserver.accbook.client.AccCategoryServiceClient;
import com.iiiiii.accbookserver.accbook.client.AssetServiceClient;
import com.iiiiii.accbookserver.accbook.client.RegularExpenseServiceClient;
import com.iiiiii.accbookserver.accbook.client.StoreServiceClient;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.dto.AccbookDTO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.dto.RequestRegistAccbookDTO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.entity.Accbook;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.ResponseStoreCodeVO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.ResponseRegExpVO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.RequestRegistStoreVO;
import com.iiiiii.accbookserver.accbook.command.domain.repository.AccbookRepository;
import com.iiiiii.accbookserver.common.InOrOut;
import com.iiiiii.accbookserver.common.InOrOutOrTransfer;
import com.iiiiii.accbookserver.common.YesOrNo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service("AccbookServiceCommand")
@Slf4j
public class AccbookService {

    private final AccbookRepository accbookRepository;
    RegularExpenseServiceClient regularExpenseServiceClient;
    AssetServiceClient assetServiceClient;
    AccCategoryServiceClient accCategoryServiceClient;
    StoreServiceClient storeServiceClient;

    @Autowired
    public AccbookService(AccbookRepository accbookRepository,
                          RegularExpenseServiceClient regularExpenseServiceClient,
                          AssetServiceClient assetServiceClient,
                          AccCategoryServiceClient accCategoryServiceClient,
                          StoreServiceClient storeServiceClient) {
        this.accbookRepository = accbookRepository;
        this.regularExpenseServiceClient = regularExpenseServiceClient;
        this.assetServiceClient = assetServiceClient;
        this.accCategoryServiceClient = accCategoryServiceClient;
        this.storeServiceClient = storeServiceClient;
    }

    @Transactional
    public Accbook registAccbook(RequestRegistAccbookDTO newAccbook) {

        Integer storeCode = null;
        Accbook accbook = new Accbook();

        // 1.'방문한 가게' 정보가 입력된 경우
        if (newAccbook.getRegistStoreDTO() != null) {
            ResponseStoreCodeVO responseStoreCodeVO = storeServiceClient.getStoreCodeByLatLng(
                    newAccbook.getRegistStoreDTO().getLatitude(), newAccbook.getRegistStoreDTO().getLongitude());

            storeCode =  (Integer) responseStoreCodeVO.getResult().get("storeCode");

            // '방문한 가게'가 가게DB에 존재하지 않는 경우 -> Store DB에 등록 후 storeCode 저장
            if (storeCode == null) {
                storeCode = registNotExistStore(newAccbook, accbook);
            }
        }

        // 2. 자산 변경
        InOrOutOrTransfer financeType = newAccbook.getFinanceType();
        changeAsset(financeType, newAccbook.getAssetCode(), newAccbook.getAmount(), newAccbook.getInAssetCode());

        // 3. 가계부 DB에 저장
        accbook.setStoreCode(storeCode);
        accbook.setCreatedAt(newAccbook.getCreatedAt());
        accbook.setTitle(newAccbook.getTitle());
        accbook.setAmount(newAccbook.getAmount());
        accbook.setIsRegular(newAccbook.getIsRegular());
        accbook.setMemberCode(newAccbook.getMemberCode());
        accbook.setAccCategoryCode(newAccbook.getAccCategoryCode());
        accbook.setAssetCode(newAccbook.getAssetCode());
        accbook.setFinanceType(newAccbook.getFinanceType());
        accbook.setInAssetCode(newAccbook.getInAssetCode());

        accbookRepository.save(accbook);
        return accbook;
    }

    @Transactional
    public Accbook modifyAccbook(int accbookCode, RequestRegistAccbookDTO modifyAccbook) {

        // 1. 가계부 세부사항이 수정되는 경우
        // 영속 상태인 엔티티 만들기
        Accbook accbook = accbookRepository.findById(accbookCode).orElseThrow(IllegalArgumentException::new);

        // 영속 엔티티에 변경된 값 저장 (기존값 + 수정값 상태로 가져온다고 가정)
        accbook.setCreatedAt(modifyAccbook.getCreatedAt());
        accbook.setTitle(modifyAccbook.getTitle());
        accbook.setIsRegular(modifyAccbook.getIsRegular());
        accbook.setMemberCode(modifyAccbook.getMemberCode());

        // 2. 방문한 가게가 추가/수정/삭제되는 경우
        if (modifyAccbook.getRegistStoreDTO() == null) {
            // 2-1. 방문한 가게가 null인 경우
            accbook.setStoreCode(null);
        } else {
            // 2-2. 방문한 가게가 추가/수정된 경우
            Integer storeCode = null;

            ResponseStoreCodeVO responseStoreCodeVO = storeServiceClient.getStoreCodeByLatLng(
                    modifyAccbook.getRegistStoreDTO().getLatitude(), modifyAccbook.getRegistStoreDTO().getLongitude());

            storeCode =  (Integer) responseStoreCodeVO.getResult().get("storeCode");

            // '방문한 가게'가 가게DB에 존재하지 않는 경우, Store DB에 등록 후 storeCode 저장
            if (storeCode == null) {
                storeCode = registNotExistStore(modifyAccbook, accbook);
                accbook.setStoreCode(storeCode);
            }
        }

        // 3. 자산이 변경된 경우
        InOrOutOrTransfer beforeFinanceType = accbook.getFinanceType();
        InOrOutOrTransfer afterFinanceType = modifyAccbook.getFinanceType();
        Integer beforeAssetCode = accbook.getAssetCode();
        Integer afterAssetCode = modifyAccbook.getAssetCode();
        Long beforeAmount = accbook.getAmount();
        Long afterAmount = modifyAccbook.getAmount();

        // 기존의 자산 복구
        if (beforeFinanceType == InOrOutOrTransfer.O) {
            changeAsset(InOrOutOrTransfer.I, beforeAssetCode, beforeAmount, null);
        } else if (beforeFinanceType == InOrOutOrTransfer.I) {
            changeAsset(InOrOutOrTransfer.O, beforeAssetCode, beforeAmount, null);
        } else if (beforeFinanceType == InOrOutOrTransfer.T) {
            changeAsset(InOrOutOrTransfer.T, accbook.getInAssetCode(), beforeAmount, beforeAssetCode); // 반대로 이체해서 복구
        }

        // 수정된 내역으로 자산 업데이트
        if (afterFinanceType == InOrOutOrTransfer.O) {
            changeAsset(afterFinanceType, afterAssetCode, afterAmount, null);
        } else if (afterFinanceType == InOrOutOrTransfer.I) {
            changeAsset(afterFinanceType, afterAssetCode, afterAmount, null);
        } else if (afterFinanceType == InOrOutOrTransfer.T) {
            changeAsset(afterFinanceType, afterAssetCode, afterAmount, modifyAccbook.getInAssetCode());
        }

        accbook.setAccCategoryCode(modifyAccbook.getAccCategoryCode());
        accbook.setAssetCode(modifyAccbook.getAssetCode());
        accbook.setAmount(modifyAccbook.getAmount());

        // 트랜젝션 커밋 시 JPA가 자동으로 변경된 엔티티를 DB에 반영 (Dirty Checking)
        return accbook;
    }
    
    @Transactional
    public void removeAccbook(Integer accbookCode) {
        Accbook accbook = accbookRepository.findById(accbookCode).orElseThrow(IllegalArgumentException::new);
        accbookRepository.delete(accbook);
    }

    /* 고정지출 자동 기입 메서드 */
    @Transactional
    @Scheduled(cron = "0 6 12 * * ?") // 매일 자정에 실행
    public void registRegularExpense() {
        // 고정지출 서비스로부터 모든 고정지출 리스트 조회
        List<ResponseRegExpVO> allResponseRegExpVO = regularExpenseServiceClient.findAllRegularExpenses();

        // 오늘 날짜 저장
        int todayDate = LocalDate.now().getDayOfMonth();

        // 오늘 날짜에 해당하는 고정지출 필터링
        List<ResponseRegExpVO> todayRegularExpenseDTO = allResponseRegExpVO.stream()
                .filter(expense -> expense.getExpenseDate() == todayDate).collect(Collectors.toList());

        // 필터링한 고정지출을 Accbook 엔티티로 변환
        List<Accbook> accbookList = todayRegularExpenseDTO.stream()
                .map(expense -> {
                    Accbook accbook = new Accbook();
                    accbook.setCreatedAt(LocalDate.now().toString());
                    accbook.setTitle(expense.getName());
                    accbook.setAmount((long) expense.getAmount());
                    accbook.setIsRegular(YesOrNo.Y);
                    accbook.setMemberCode(expense.getMemberCode());
                    accbook.setAccCategoryCode(expense.getAccCategoryCode());
                    accbook.setStoreCode(null);
                    accbook.setAssetCode(expense.getAssetCode());
                    return accbook;
                }).collect(Collectors.toList());

        // 가계부DB에 저장
        accbookRepository.saveAll(accbookList);
    }

    private Integer registNotExistStore(RequestRegistAccbookDTO newAccbook, Accbook accbook) {

        // 가게DB에 새 가게 등록
        ResponseStoreCodeVO responseStoreCodeVO;
        RequestRegistStoreVO registerStoreVO = new RequestRegistStoreVO(
                newAccbook.getRegistStoreDTO().getStoreName(),
                newAccbook.getRegistStoreDTO().getStoreAddress(),
                newAccbook.getRegistStoreDTO().getLatitude(),
                newAccbook.getRegistStoreDTO().getLongitude()
        );
        storeServiceClient.registerStore(registerStoreVO);

        // 등록된 가게 storeCode 다시 저장
        responseStoreCodeVO = storeServiceClient.getStoreCodeByLatLng(
                newAccbook.getRegistStoreDTO().getLatitude(),
                newAccbook.getRegistStoreDTO().getLongitude());
        return (Integer) responseStoreCodeVO.getResult().get("storeCode");

    }
    
    private void changeAsset(InOrOutOrTransfer financeType, Integer assetCode, Long amount, Integer inAssetCode) {
        if (financeType == InOrOutOrTransfer.I) {           // 수입인 경우
            assetServiceClient.modifyAssetByIn(assetCode, amount);
        } else if (financeType == InOrOutOrTransfer.O) {    // 지출인 경우
            assetServiceClient.modifyAssetByOut(assetCode, amount);
        } else if (financeType == InOrOutOrTransfer.T) {    // 이체인 경우
            assetServiceClient.modifyAssetByOut(assetCode, amount);     // 출금 자산에서 -
            assetServiceClient.modifyAssetByIn(inAssetCode, amount);    // 입금 자산에서 +
        }
    }

}