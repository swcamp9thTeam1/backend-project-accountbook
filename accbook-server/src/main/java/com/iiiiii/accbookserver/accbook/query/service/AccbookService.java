package com.iiiiii.accbookserver.accbook.query.service;

import com.iiiiii.accbookserver.accbook.query.dto.AccbookCategoryStatsDTO;
import com.iiiiii.accbookserver.accbook.query.dto.AccbookDTO;
import com.iiiiii.accbookserver.accbook.query.dto.AccbookDetailDTO;
import com.iiiiii.accbookserver.accbook.query.dto.AccbookTop3CategoryDTO;
import com.iiiiii.accbookserver.accbook.query.repository.AccbookMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AccbookService {

    private final AccbookMapper accbookMapper;

    public AccbookService(AccbookMapper accbookMapper) {
        this.accbookMapper = accbookMapper;
    }

    public List<AccbookDTO> findDailyAccbookBy(int memberCode, String findDate) {
        return accbookMapper.selectDailyAccbookBy(memberCode, findDate);
    }


    public List<AccbookDTO> findWeeklyAccbookBy(Integer memberCode, String findDate, Integer weekNo) {
        return accbookMapper.selectWeeklyAccbookBy(memberCode, findDate, weekNo);
    }

    public List<AccbookDTO> findMonthlyAccbookBy(int memberCode, String findDate) {
        return accbookMapper.selectMonthlyAccbookBy(memberCode, findDate);
    }

    public List<AccbookTop3CategoryDTO> findMonthlyTop3CategoriesBy(Integer memberCode, String findDateString) {
        return accbookMapper.selectMonthlyTop3Categories(memberCode, findDateString);
    }

    public List<AccbookCategoryStatsDTO> findMonthlyCategoryStatBy(Integer memberCode, String findDateString) {
        return accbookMapper.selectMonthlyCategoryStats(memberCode, findDateString);
    }

    public AccbookDetailDTO findAccbookDetailBy(Integer accbookCode) {
        return accbookMapper.selectAccbookDetailBy(accbookCode);
    }


}
