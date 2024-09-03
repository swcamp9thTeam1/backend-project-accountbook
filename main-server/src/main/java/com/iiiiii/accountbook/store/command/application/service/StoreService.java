package com.iiiiii.accountbook.store.command.application.service;

import com.iiiiii.accountbook.common.YesOrNo;
import com.iiiiii.accountbook.exception.NotValidRequestException;
import com.iiiiii.accountbook.store.command.domain.aggregate.entity.Store;
import com.iiiiii.accountbook.store.command.domain.aggregate.vo.RegisterStoreVO;
import com.iiiiii.accountbook.store.command.domain.aggregate.vo.RequestModifyGoodStoreVO;
import com.iiiiii.accountbook.store.command.domain.aggregate.vo.RequestModifyStoreVO;
import com.iiiiii.accountbook.store.command.domain.repository.StoreRepository;
import com.iiiiii.accountbook.exception.NotFoundStoreException;
import com.iiiiii.accountbook.store.query.dto.StoreDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public void registerGoodStore(MultipartFile file) throws Exception {

        // 엑셀 파일인지 확인
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!"xls".equals(extension) && !"xlsx".equals(extension)) {
            throw new NotValidRequestException("엑셀 파일만 업로드 해주세요.");
        }

        List<StoreDTO> newStores = parsingStoreExcel(file, extension);

        List<Store> storeEntities = newStores.stream().map(newStoreDTO -> new Store(
                newStoreDTO.getStoreName(),
                newStoreDTO.getAddress(),
                newStoreDTO.getLatitude(),
                newStoreDTO.getLongitude(),
                newStoreDTO.getIsGood(),
                newStoreDTO.getGoodMenuName(),
                newStoreDTO.getGoodMenuPrice()
        )).collect(Collectors.toList());

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

    @Transactional
    public void registerStore(RegisterStoreVO registerStoreVO) {

        // VO -> Entity (DTO는 VO와 모양새가 똑같을 것 같아서 VO 그대로 사용)
        Store newStore = new Store();
        newStore.setStoreName(registerStoreVO.getStoreName());
        newStore.setAddress(registerStoreVO.getStoreAddress());
        newStore.setLatitude(registerStoreVO.getLatitude());
        newStore.setLongitude(registerStoreVO.getLongitude());
        newStore.setIsGood(YesOrNo.N);

        storeRepository.save(newStore);
    }

    @Transactional
    public void modifyStore(int storeCode, RequestModifyStoreVO requestBody) throws Exception {
        Store foundStore = storeRepository.findById(storeCode).orElseThrow(NotFoundStoreException::new);

        foundStore.setStoreName(requestBody.getName());
        foundStore.setAddress(requestBody.getAddress());
        foundStore.setLatitude(requestBody.getLatitude());
        foundStore.setLongitude(requestBody.getLongitude());
    }

    @Transactional
    public void modifyGoodStoreToN(int storeCode) {

        // 가게를 삭제하지 않고, 착한가격업소 정보만 비운다. (UPDATE)
        Store foundStore = storeRepository.findById(storeCode).orElseThrow(IllegalArgumentException::new);
        foundStore.setIsGood(YesOrNo.N);
        foundStore.setGoodMenuName(null);
        foundStore.setGoodMenuPrice(null);
    }

    @Transactional
    public void modifyGoodStore(int storeCode, RequestModifyGoodStoreVO requestModifyGoodStoreVO)
            throws Exception {
        Store foundStore = storeRepository.findById(storeCode)
                .orElseThrow(NotFoundStoreException::new);

        foundStore.setGoodMenuName(requestModifyGoodStoreVO.getGoodMenuName());
        foundStore.setGoodMenuPrice(requestModifyGoodStoreVO.getGoodMenuPrice());
    }

    @Transactional
    public void removeStore(int storeCode) throws Exception {

        if (!storeRepository.existsById(storeCode)) {
            throw new NotFoundStoreException();
        }

        storeRepository.deleteById(storeCode);
    }
}
