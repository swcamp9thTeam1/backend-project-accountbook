package com.iiiiii.accountbook.regular_expense.query.service;

import com.iiiiii.accountbook.regular_expense.query.dto.RegularExpenseDTO;
import com.iiiiii.accountbook.regular_expense.query.repository.RegularExpenseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("queryRegularExpenseService")
public class RegularExpenseService {
    private RegularExpenseMapper reMapper;

    @Autowired
    public RegularExpenseService(RegularExpenseMapper reMapper) {
        this.reMapper = reMapper;
    }

    // 고정 지출 목록 조회
    public List<RegularExpenseDTO> findAllRegularExpenses() {
        List<RegularExpenseDTO> regularExpenses = reMapper.findAllRegularExpenses();

        return regularExpenses;
    }

    // 회원별 고정 지출 목록 조회
    public List<RegularExpenseDTO> findOneMemberRegularExpenses(int memberCode) {
        List<RegularExpenseDTO> regularExpenses = reMapper.findOneMemberRegularExpenses(memberCode);

        return regularExpenses;

    }

    // 고정 지출 번호로 조회
    public RegularExpenseDTO findOneRegularExpenses(int regularExpenseCode) {
        RegularExpenseDTO result = reMapper.findOneRegularExpense(regularExpenseCode);

        return result;
    }
}
