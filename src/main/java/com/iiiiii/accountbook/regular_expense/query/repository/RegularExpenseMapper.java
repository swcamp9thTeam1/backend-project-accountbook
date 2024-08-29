package com.iiiiii.accountbook.regular_expense.query.repository;

import com.iiiiii.accountbook.regular_expense.query.dto.RegularExpenseDTO;
import com.iiiiii.accountbook.regular_expense.query.dto.ResponseRegularExpenseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegularExpenseMapper {

    List<RegularExpenseDTO> findAllRegularExpenses();

    List<ResponseRegularExpenseDTO> findOneMemberRegularExpenses(int memberCode);

    RegularExpenseDTO findOneRegularExpense(int regularExpenseCode);
}
