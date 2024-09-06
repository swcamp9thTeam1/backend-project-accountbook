package com.iiiiii.accountbook.regular_expense.command.service;

import com.iiiiii.accountbook.regular_expense.command.domain.aggregate.RegularExpense;
import com.iiiiii.accountbook.regular_expense.command.application.service.RegularExpenseService;
import com.iiiiii.accountbook.regular_expense.query.dto.RegularExpenseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegularExpenseServiceTests {

    private RegularExpenseService regularExpenseService;
    private com.iiiiii.accountbook.regular_expense.query.service.RegularExpenseService queryService;

    @Autowired
    public RegularExpenseServiceTests(RegularExpenseService regularExpenseService,
                                      com.iiiiii.accountbook.regular_expense.query.service.RegularExpenseService queryService) {
        this.regularExpenseService = regularExpenseService;
        this.queryService = queryService;
    }

    @DisplayName("고정 지출 생성 확인 테스트")
    @Test
    public void testRegistRegularExpense() {
        int result = regularExpenseService.registRegularExpense(
                new RegularExpense(10, "고정지출테스트", 50000, 1, 1, 1)).getCode();
        assertTrue(result > 0);
    }

    @DisplayName("고정 지출 수정 확인 테스트")
    @Test
    public void testModifyRegularExpense() {
        String result = regularExpenseService.modifyRegularExpense(
                new RegularExpense(17, 7, "고정지출수정테스트", 70000, 7, 3, 3)).getName();
        assertEquals("고정지출수정테스트", result);
    }

    @DisplayName("고정 지출 삭제 확인 테스트")
    @Test
    public void testDeleteRegularExpense() {
        regularExpenseService.deleteRegularExpense(
                new RegularExpense(16, 1, "예금", 50000, 1, 1, 1));
        RegularExpenseDTO result = queryService.findOneRegularExpenses(16);
        assertNull(result);
    }
}
