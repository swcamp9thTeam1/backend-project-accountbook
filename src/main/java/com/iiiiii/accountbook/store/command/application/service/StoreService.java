package com.iiiiii.accountbook.store.command.application.service;

import com.iiiiii.accountbook.common.YesOrNo;
import com.iiiiii.accountbook.store.command.domain.aggregate.entity.Store;
import com.iiiiii.accountbook.store.command.domain.repository.StoreRepository;
import com.iiiiii.accountbook.store.query.dto.StoreDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("StoreServiceCommand")
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public void registerStore(MultipartFile file, String extension) throws Exception {
        List<StoreDTO> newStores = parsingStoreExcel(file, extension);

        List<Store> storeEntities = newStores.stream().map(newStoreDTO -> {
            Store store = new Store();
            store.setStoreName(newStoreDTO.getStoreName());
            store.setAddress(newStoreDTO.getAddress());
            store.setLatitude(newStoreDTO.getLatitude());
            store.setLongitude(newStoreDTO.getLongitude());
            store.setIsGood(newStoreDTO.getIsGood());
            store.setGoodMenuName(newStoreDTO.getGoodMenuName());
            store.setGoodMenuPrice(newStoreDTO.getGoodMenuPrice());

            return store;
        }).collect(Collectors.toList());

        storeRepository.saveAll(storeEntities);
    }

    private List<StoreDTO> parsingStoreExcel(MultipartFile file, String extension) throws Exception {
        List<StoreDTO> newStores = new ArrayList<>();
        Workbook workbook = null;

        if ("xlsx".equals(extension)) {     // 엑셀 2007 이상
            workbook = new XSSFWorkbook(file.getInputStream());
        } else {                            // 엑셀 2007 미만 버전
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        // 엑셀 파일의 첫번째 시트 가져오기
        Sheet sheet = workbook.getSheetAt(0);

        // 전체 행의 개수만큼 돌려서 데이터 가져오기
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);              // 행 1개 가져오기

            if (row == null) continue;

//            log.info("행번호: {}", i);

            StoreDTO newStore = new StoreDTO();     // 착한가격업소 데이터 셋팅을 위해
            newStore.setIsGood(YesOrNo.Y);
            newStore.setLatitude("37.497436");      // TODO:: 위도 임시데이터
            newStore.setLongitude("126.927531");    // TODO:: 경도 임시데이터

            for (int j = 0; j < row.getLastCellNum(); j++) {   // 행의 cell 가져오기
                Cell cell = row.getCell(j);

//                log.info("행번호 {} CELL: {}", i, cell);

                if (cell == null) break;

                // 0번 인덱스에 들어있는 데이터를 Number형으로 바꿨을 때 예외가 나면 다음 행으로 건너뛰기
                if (j == 0) {
                    String stringNo = cell.getStringCellValue();

                    try {
                        int no = Integer.parseInt(stringNo);
                    } catch (NumberFormatException e) {
                        break;
                    }
                }

//                log.info("{}, {}, {}", j, cell, cell.getCellType());

                if (j == 2) {
                    newStore.setStoreName(cell.getStringCellValue());
                }
                if (j == 3) newStore.setGoodMenuName(cell.getStringCellValue());
                if (j == 4) {
                    int price = (int) cell.getNumericCellValue();
                    newStore.setGoodMenuPrice(price);
                }
                if (j == 6) newStore.setAddress(cell.getStringCellValue());
            }

//            log.info("{}", newStore);

            // 값이 있을 때에만 추가
            if (newStore.getStoreName() != null && newStore.getGoodMenuName() != null &&
            newStore.getGoodMenuPrice() != null && newStore.getAddress() != null &&
            newStore.getLatitude() != null && newStore.getLongitude() != null) {
                newStores.add(newStore);
            }
        }

        return newStores;
    }
}