package com.iiiiii.accountbook.regular_expense.command.service;

import com.iiiiii.accountbook.regular_expense.command.domain.aggregate.RegularExpense;
import com.iiiiii.accountbook.regular_expense.command.application.service.RegularExpenseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RegularExpenseServiceTests {

    @Autowired
    private RegularExpenseService regularExpenseService;

    @DisplayName("고정 지출 생성 확인 테스트")
    @Test
    public void testRegistRegularExpense() {
        regularExpenseService.registRegularExpense(
                new RegularExpense(10, "정기지출테스트", 50000, 1, 1, 1));
    }

    @DisplayName("고정 지출 수정 확인 테스트")
    @Test
    public void testModifyRegularExpense() {
        regularExpenseService.modifyRegularExpense(
                new RegularExpense(17, 7, "정기지출77", 70000, 7, 3, 3));
    }



    @DisplayName("멤버의 고정 지출 삭제 확인 테스트")
    @Test
    public void testDeleteRegularExpense() {
        regularExpenseService.deleteRegularExpense(
                new RegularExpense(16, 1, "예금", 50000, 1, 1, 1));
    }
}
