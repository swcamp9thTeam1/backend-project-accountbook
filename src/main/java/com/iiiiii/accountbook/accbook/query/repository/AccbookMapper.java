package com.iiiiii.accountbook.accbook.query.repository;

import com.iiiiii.accountbook.accbook.query.dto.AccbookDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AccbookMapper {
    List<AccbookDTO> selectDailyAccbookBy(int memberCode, Date findDate);
}
