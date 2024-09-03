package com.iiiiii.accountbook.regular_expense.command.application.service;

import com.iiiiii.accountbook.regular_expense.command.domain.aggregate.RegularExpense;
import com.iiiiii.accountbook.regular_expense.command.domain.aggregate.RegularExpenseEntity;
import com.iiiiii.accountbook.regular_expense.command.domain.repository.RegularExpenseRepository;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service("commandRegularExpenseService")
public class RegularExpenseService {
    private RegularExpenseRepository regularExpenseRepository;
    private ModelMapper modelMapper;

    @Autowired
    public RegularExpenseService(RegularExpenseRepository regularExpenseRepository, ModelMapper modelMapper) {
        this.regularExpenseRepository = regularExpenseRepository;
        this.modelMapper = modelMapper;
    }

    // 고정 지출 설정(입력값: "expenseDate", "name", "amount", "memberCode", "assetCode", "accCategoryCode")
    public void registRegularExpense(RegularExpense newRegularExpense) {
        RegularExpenseEntity regularExpenseEntity = modelMapper.map(newRegularExpense, RegularExpenseEntity.class);
        regularExpenseRepository.save(regularExpenseEntity);
    }

    // 고정 지출 수정(입력값: "code", "expenseDate", "name", "amount", "memberCode", "assetCode", "accCategoryCode")
    public void modifyRegularExpense(RegularExpense modifyRegularExpense) {
        RegularExpenseEntity newEntity = modelMapper.map(modifyRegularExpense, RegularExpenseEntity.class);
        regularExpenseRepository.saveAndFlush(newEntity);
    }

    // 고정 지출 삭제(입력값: "code", "expenseDate", "name", "amount", "memberCode", "assetCode", "accCategoryCode")
    public void deleteRegularExpense(RegularExpense deleteRegularExpense) {
        RegularExpenseEntity regularExpenseEntity = modelMapper.map(deleteRegularExpense, RegularExpenseEntity.class);
        regularExpenseRepository.delete(regularExpenseEntity);
    }
}
