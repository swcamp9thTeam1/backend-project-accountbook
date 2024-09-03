package com.iiiiii.accbookserver.accbook.command.application.service;;

import com.iiiiii.accbookserver.accbook.client.RegularExpenseServiceClient;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.dto.AccbookDTO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.entity.Accbook;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.ResponseRegExpVO;
import com.iiiiii.accbookserver.accbook.command.domain.repository.AccbookRepository;
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
//    private final RegularExpenseService regularExpenseServiceQuery;
//    private final com.iiiiii.accountbook.store.query.service.StoreService storeServiceQuery;
//    private final com.iiiiii.accountbook.store.command.application.service.StoreService storeServiceCommand;
//    private final QueryAccCategoryService accCategoryServiceQuery;
//    private final AssetService assetServiceCommand;

    @Autowired
    public AccbookService(AccbookRepository accbookRepository, RegularExpenseServiceClient regularExpenseServiceClient
//            ,
//                          RegularExpenseService regularExpenseServiceQuery,
//                          com.iiiiii.accountbook.store.query.service.StoreService storeServiceQuery,
//                          com.iiiiii.accountbook.store.command.application.service.StoreService storeServiceCommand,
//                          QueryAccCategoryService accCategoryServiceQuery,
//                          AssetService assetServiceCommand
    ) {
        this.accbookRepository = accbookRepository;
        this.regularExpenseServiceClient = regularExpenseServiceClient;
//        this.regularExpenseServiceQuery = regularExpenseServiceQuery;
//        this.storeServiceQuery = storeServiceQuery;
//        this.storeServiceCommand = storeServiceCommand;
//        this.accCategoryServiceQuery = accCategoryServiceQuery;
//        this.assetServiceCommand = assetServiceCommand;
    }

//    @Transactional
//    public Accbook registAccbook(RequestRegistAccbookDTO newAccbook) {
//
//        Integer storeCode;
//        Accbook accbook = new Accbook();
//
//        // 1.'방문한 가게' 정보가 입력된 경우
//        if (newAccbook.getStoreName() != null) {
//            storeCode = storeServiceQuery.isExistStoreByLatLng(newAccbook.getLatitude(), newAccbook.getLongitude());
//
//            log.info("***** AccbookService - storeCode: {}", storeCode);
//
//            // '방문한 가게'가 가게DB에 존재하지 않는 경우 -> Store DB에 등록 후 storeCode 저장
//            if (storeCode == null) {
//
//                // RegisterStoreVO 생성 및 정보 저장
//                RegisterStoreVO registerStoreVO = new RegisterStoreVO(
//                        newAccbook.getStoreName(),
//                        newAccbook.getStoreAddress(),
//                        newAccbook.getLatitude(),
//                        newAccbook.getLongitude()
//                );
//
//                log.info("***** AccbookService - newAccbook: {}", newAccbook);
//
//                storeServiceCommand.registerStore(registerStoreVO); // 가게DB 등록 메서드 호출
//                log.info("***** AccbookService - 가게 등록 registerStoreVO: {}", registerStoreVO);
//
//                storeCode = storeServiceQuery.isExistStoreByLatLng(newAccbook.getLatitude(), newAccbook.getLongitude()); // 등록 후 storeCode 다시 저장
//                accbook.setStoreCode(storeCode);
//            }
//        }
//
//
//        // 2. 자산 변경
////        if (accCategoryServiceQuery.findOneAccCategory(newAccbook.getAccCategoryCode()).getFinanceType() == InOrOut.I) {           // 수입인 경우
////            assetServiceCommand.modifyAssetByIn(newAccbook.getAssetCode(), newAccbook.getAmount());
////        } else if (accCategoryServiceQuery.findOneAccCategory(newAccbook.getAccCategoryCode()).getFinanceType() == InOrOut.O) {    // 지출인 경우
////            assetServiceCommand.modifyAssetByOut(newAccbook.getAssetCode(), newAccbook.getAmount());
////        }
//
//        // 3. 가계부 DB에 저장
//        log.info("***** AccbookService - db 전 : accbook: {}", accbook);
//
//        accbook.setCreatedAt(newAccbook.getCreatedAt());
//        accbook.setTitle(newAccbook.getTitle());
//        accbook.setAmount(newAccbook.getAmount());
//        accbook.setIsRegular(newAccbook.getIsRegular());
//        accbook.setMemberCode(newAccbook.getMemberCode());
//        accbook.setAccCategoryCode(newAccbook.getAccCategoryCode());
//        accbook.setAssetCode(newAccbook.getAssetCode());
//
//        log.info("***** AccbookService - db 후 : accbook: {}", accbook);
//
//
//        accbookRepository.save(accbook);
//        return accbook;
//    }

    @Transactional
    public Accbook modifyAccbook(int accbookCode, AccbookDTO modifyAccbook) {

        // 영속 상태인 엔티티 만들기
        Accbook accbook = accbookRepository.findById(accbookCode).orElseThrow(IllegalArgumentException::new);

        // 영속 엔티티에 변경된 값 저장 (기존값 + 수정값 상태로 가져온다고 가정)
        accbook.setCreatedAt(modifyAccbook.getCreatedAt());
        accbook.setTitle(modifyAccbook.getTitle());
        accbook.setAmount(modifyAccbook.getAmount());
        accbook.setIsRegular(modifyAccbook.getIsRegular());
        accbook.setMemberCode(modifyAccbook.getMemberCode());
        accbook.setAccCategoryCode(modifyAccbook.getAccCategoryCode());
        accbook.setStoreCode(modifyAccbook.getStoreCode());
        accbook.setAssetCode(modifyAccbook.getAssetCode());

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
    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
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
}