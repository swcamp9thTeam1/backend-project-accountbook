package com.iiiiii.accountbook.accbook.query.service;

import com.iiiiii.accountbook.accbook.query.dto.AccbookCategoryStatsDTO;
import com.iiiiii.accountbook.accbook.query.dto.AccbookDTO;
import com.iiiiii.accountbook.accbook.query.dto.AccbookTop3CategoryDTO;
import com.iiiiii.accountbook.accbook.query.repository.AccbookMapper;
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

    public List<AccbookDTO> findDailyAccbookBy(int memberCode, String findDateString) {

        java.util.Date findDate = convertStringToDate(findDateString);
        return accbookMapper.selectDailyAccbookBy(memberCode, findDate);
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

    private Date convertStringToDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString);
        }
    }
}
