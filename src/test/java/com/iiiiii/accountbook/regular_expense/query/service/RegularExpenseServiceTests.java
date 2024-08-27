package com.iiiiii.accountbook.regular_expense.query.service;

import com.iiiiii.accountbook.regular_expense.query.dto.RegularExpenseDTO;
import com.iiiiii.accountbook.regular_expense.query.dto.ResponseRegularExpenseDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RegularExpenseServiceTests {

    @Autowired
    private RegularExpenseService regularExpenseService;

    @DisplayName("고정 지출 목록 조회 확인 테스트")
    @Test
    public void testFindAllRegularExpenses() {
        System.out.println("start!");
        List<RegularExpenseDTO> list = regularExpenseService.findAllRegularExpenses();

        assertTrue(!list.isEmpty());

        list.forEach(System.out::println);

    }

    @DisplayName("멤버의 고정 지출 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void testFindOneMemberRegularExpenses(int memberCode) {
        List<ResponseRegularExpenseDTO> list = regularExpenseService.findOneMemberRegularExpenses(memberCode);

        assertTrue(!list.isEmpty());

        list.forEach(System.out::println);
    }
}
