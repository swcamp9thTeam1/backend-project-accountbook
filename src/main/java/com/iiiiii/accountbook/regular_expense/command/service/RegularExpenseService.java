package com.iiiiii.accountbook.regular_expense.command.service;

import com.iiiiii.accountbook.regular_expense.command.dto.RegularExpenseDTO;
import com.iiiiii.accountbook.regular_expense.command.repository.CommandRegularExpenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commandService")
public class RegularExpenseService {
    private CommandRegularExpenseMapper reMapper;

    @Autowired
    public RegularExpenseService(CommandRegularExpenseMapper reMapper) {
        this.reMapper = reMapper;
    }

    // 고정 지출 설정
    public int registRegularExpense(RegularExpenseDTO newRegularExpense) {
        int result = reMapper.registRegularExpense(newRegularExpense);

        return result;
    }

    // 고정 지출 삭제(member_code)
    public int deleteRegularExpense(int regularExpenseCode) {
        int result = reMapper.deleteRegularExpense(regularExpenseCode);

        return result;
    }
}
