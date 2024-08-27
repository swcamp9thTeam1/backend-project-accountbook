package com.iiiiii.accountbook.accbook.query.repository;

import com.iiiiii.accountbook.accbook.query.dto.AccbookDTO;
import com.iiiiii.accountbook.accbook.query.dto.AccbookTop3CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AccbookMapper {
    List<AccbookDTO> selectDailyAccbookBy(int memberCode, Date findDate);

    List<AccbookDTO> selectMonthlyAccbookBy(int memberCode, String findDate);

    List<AccbookTop3CategoryDTO> selectMonthlyTop3Categories(Integer memberCode, String findDate);
}
