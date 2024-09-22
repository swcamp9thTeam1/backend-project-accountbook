package com.iiiiii.accbookserver.accbook.query.repository;

import com.iiiiii.accbookserver.accbook.query.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AccbookMapper {
    List<AccbookDTO> selectDailyAccbookBy(int memberCode, String findDate);

    List<AccbookDTO> selectWeeklyAccbookBy(Integer memberCode, String findDate, Integer weekNo);

    List<AccbookDTO> selectMonthlyAccbookBy(int memberCode, String findDate);

    List<AccbookTop3CategoryDTO> selectMonthlyTop3Categories(Integer memberCode, String findDate);

    List<AccbookCategoryStatsDTO> selectMonthlyCategoryStats(Integer memberCode, String findDate);

    AccbookDetailDTO selectAccbookDetailBy(Integer accbookCode);

    List<AccbookMonthlyEvalDTO> selectMonthlyEvalBy(Integer memberCode, String findDate, Integer budget);
}
