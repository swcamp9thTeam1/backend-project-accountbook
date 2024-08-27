package com.iiiiii.accountbook.regular_expense.command.repository;

import com.iiiiii.accountbook.regular_expense.command.dto.RegularExpenseDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommandRegularExpenseMapper {

    int registRegularExpense(RegularExpenseDTO newRegularExpense);

    int deleteRegularExpense(int regularExpenseCode);
}
