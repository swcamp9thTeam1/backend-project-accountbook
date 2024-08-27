package com.iiiiii.accountbook.regular_expense.command.service;

import com.iiiiii.accountbook.regular_expense.command.dto.RegularExpenseDTO;
import com.iiiiii.accountbook.regular_expense.command.service.RegularExpenseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class RegularExpenseServiceTests {

    @Autowired
    private RegularExpenseService regularExpenseService;

    @DisplayName("고정 지출 생성 확인 테스트")
    @Test
    public void testRegistRegularExpense() {
        RegularExpenseDTO newExpense = new RegularExpenseDTO(1, "예금", 50000, 1, 1, 1);

        int result = regularExpenseService.registRegularExpense(newExpense);

        Assertions.assertTrue(result == 1);

    }

    @DisplayName("멤버의 고정 지출 삭제 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void testDeleteRegularExpense(int regularExpenseCode) {
        int result = regularExpenseService.deleteRegularExpense(regularExpenseCode);

        Assertions.assertTrue(result == 1);
    }
}
