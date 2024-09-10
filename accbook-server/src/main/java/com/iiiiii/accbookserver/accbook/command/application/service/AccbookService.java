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
        InOrOut financeType = accCategoryServiceClient.findOneAccCategory(newAccbook.getAccCategoryCode()).getFinanceType();
        changeAsset(financeType, newAccbook.getAssetCode(), newAccbook.getAmount());

        // 3. 가계부 DB에 저장
        accbook.setStoreCode(storeCode);
        accbook.setCreatedAt(newAccbook.getCreatedAt());
        accbook.setTitle(newAccbook.getTitle());
        accbook.setAmount(newAccbook.getAmount());
        accbook.setIsRegular(newAccbook.getIsRegular());
        accbook.setMemberCode(newAccbook.getMemberCode());
        accbook.setAccCategoryCode(newAccbook.getAccCategoryCode());
        accbook.setAssetCode(newAccbook.getAssetCode());

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
            }
        }

        // 3. 자산이 변경된 경우
        /* 경우의 수
         * 3-1. 카테고리가 변경된 경우 (ex. 지출 -> 수입)
         * 3-2. 사용한 자산이 변경된 경우 (ex. 국민카드 -> 신한카드)
         * 3-3. 사용 금액만 변경된 경우 (ex. 1000원 지출 -> 3000원 지출)
         * 3-4. 혼합
         * */
        InOrOut beforeFinanceType = accCategoryServiceClient.findOneAccCategory(accbook.getAccCategoryCode()).getFinanceType();
        InOrOut afterFinanceType = accCategoryServiceClient.findOneAccCategory(modifyAccbook.getAccCategoryCode()).getFinanceType();
        Integer beforeAssetCode = accbook.getAssetCode();
        Integer afterAssetCode = modifyAccbook.getAssetCode();

        if (beforeFinanceType == afterFinanceType) {    // 수입/지출이 변경되지 않은 경우
            /*
             * 1000 지출 -> 2000 지출: 지출 1000원
             * 2000 지출 -> 1000 지출: 지출 -1000원
             * 1000 수입 -> 2000 수입: 수입 1000원
             * 2000 수입 -> 1000 수입: 수입 -1000원
             * - 내역을 저장한다면 어떻게 저장해야 할 것인지 고려 필요
             * */
            if (beforeAssetCode == afterAssetCode) {    // 자산 코드가 변경되지 않은 경우
                changeAsset(afterFinanceType, afterAssetCode, modifyAccbook.getAmount() - accbook.getAmount());
            } else {    // 자산 코드가 변경된 경우
                changeAsset(beforeFinanceType, beforeAssetCode, -accbook.getAmount());      // 기존의 자산 복구
                changeAsset(afterFinanceType, afterAssetCode, modifyAccbook.getAmount());   // 변경된 자산 변동
            }
        } else {        // 수입/지출이 변경된 경우
            changeAsset(beforeFinanceType, beforeAssetCode, -accbook.getAmount());          // 기존의 자산 복구
            changeAsset(afterFinanceType, afterAssetCode, modifyAccbook.getAmount());       // 변경된 자산 변동
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
    
    private void changeAsset(InOrOut financeType, Integer assetCode, Long amount) {
        if (financeType == InOrOut.I) {           // 수입인 경우
            assetServiceClient.modifyAssetByIn(assetCode, amount);
        } else if (financeType == InOrOut.O) {    // 지출인 경우
            assetServiceClient.modifyAssetByOut(assetCode, amount);
        }
    }

}