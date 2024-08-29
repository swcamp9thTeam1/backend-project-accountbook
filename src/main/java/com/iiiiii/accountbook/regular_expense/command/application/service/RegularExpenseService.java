package com.iiiiii.accountbook.regular_expense.command.application.service;

import com.iiiiii.accountbook.regular_expense.command.domain.aggregate.RegularExpense;
import com.iiiiii.accountbook.regular_expense.command.domain.aggregate.RegularExpenseEntity;
import com.iiiiii.accountbook.regular_expense.command.domain.repository.RegularExpenseRepository;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service("commandService")
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

        // if 0<expenseDate<32
            // if 0<amount
                // if memberCode in member.code table
                    // if assetCode in asset.code table
                        // if accCategoryCode in acc_category.code table

        RegularExpenseEntity regularExpenseEntity = modelMapper.map(newRegularExpense, RegularExpenseEntity.class);
        regularExpenseRepository.save(regularExpenseEntity);
    }

    // 고정 지출 수정(입력값: "code", "expenseDate", "name", "amount", "memberCode", "assetCode", "accCategoryCode")
    public void modifyRegularExpense(RegularExpense modifyRegularExpense) {

        // if code로 찾은 고정지출의 memberCode가 일치할 때
            // if 0<expenseDate<32
                // if 0<amount
                    // if assetCode in asset.code table
                        // if accCategoryCode in acc_category.code table

        RegularExpenseEntity newEntity = modelMapper.map(modifyRegularExpense, RegularExpenseEntity.class);
        regularExpenseRepository.saveAndFlush(newEntity);
    }

    // 고정 지출 삭제(입력값: "code", "expenseDate", "name", "amount", "memberCode", "assetCode", "accCategoryCode")
    public void deleteRegularExpense(RegularExpense deleteRegularExpense) {
        // if code로 찾은 고정지출이 존재할 때
        RegularExpenseEntity regularExpenseEntity = modelMapper.map(deleteRegularExpense, RegularExpenseEntity.class);
        regularExpenseRepository.delete(regularExpenseEntity);
    }
}
