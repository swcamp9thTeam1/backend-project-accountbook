package com.iiiiii.accbookserver.accbook.command.application.service;

import com.iiiiii.accbookserver.accbook.command.domain.aggregate.dto.AccbookDTO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.entity.Accbook;
import com.iiiiii.accbookserver.accbook.command.domain.repository.AccbookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

;import java.time.LocalDate;
import java.util.List;


@Service("AccbookServiceCommand")
@Slf4j
public class AccbookService {

    private final AccbookRepository accbookRepository;
//    private final RegularExpenseService regularExpenseServiceQuery;

    @Autowired
    public AccbookService(AccbookRepository accbookRepository
//                          , RegularExpenseService regularExpenseServiceQuery
                          ) {
        this.accbookRepository = accbookRepository;
//        this.regularExpenseServiceQuery = regularExpenseServiceQuery;
    }

    @Transactional
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

//        accbookRepository.save(accbook);
        return accbook;
    }

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
//    @Transactional
//    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
//    public void registRegularExpense() {
//        log.info("모든 고정지출 리스트 찾기 시작");
//
//        // 고정지출 서비스로부터 모든 고정지출 리스트 조회
//        List<RegularExpenseDTO> allRegularExpenseDTO = regularExpenseServiceQuery.findAllRegularExpenses();
//        log.info("모든 고정지출 리스트", allRegularExpenseDTO);
//
//        // 오늘 날짜 저장
//        int todayDate = LocalDate.now().getDayOfMonth();
//
//        // 오늘 날짜에 해당하는 고정지출 필터링
//        List<RegularExpenseDTO> todayRegularExpenseDTO = allRegularExpenseDTO.stream()
//                .filter(expense -> expense.getExpenseDate() == todayDate).collect(Collectors.toList());
//
//        log.info("오늘 고정지출 리스트", todayRegularExpenseDTO);
//
//        // 필터링한 고정지출을 Accbook 엔티티로 변환
//        List<Accbook> accbookList = todayRegularExpenseDTO.stream()
//                .map(expense -> {
//                    Accbook accbook = new Accbook();
//                    accbook.setCreatedAt(LocalDate.now().toString());
//                    accbook.setTitle(expense.getName());
//                    accbook.setAmount((long) expense.getAmount()); // RegularExpenseDTO의 amount -> Long으로 수정 필요
//                    accbook.setIsRegular(YesOrNo.Y);
//                    accbook.setMemberCode(expense.getMemberCode());
//                    accbook.setAccCategoryCode(expense.getAccCategoryCode());
//                    accbook.setStoreCode(null);
//                    accbook.setAssetCode(expense.getAssetCode());
//                    return accbook;
//                }).collect(Collectors.toList());
//
//        // 가계부DB에 저장
//        accbookRepository.saveAll(accbookList);
//    }
}
