package com.iiiiii.accountbook.regular_expense.command.application.controller;

import com.iiiiii.accountbook.regular_expense.command.application.service.RegularExpenseService;
import com.iiiiii.accountbook.regular_expense.command.domain.aggregate.RegularExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regular-expense")
public class CommandRegularExpenseController {
    private RegularExpenseService regularExpenseService;

    @Autowired
    public CommandRegularExpenseController(RegularExpenseService regularExpenseService) {
        this.regularExpenseService = regularExpenseService;
    }

    @PostMapping("/regist")
    public ResponseEntity<?> registRegularExpenses(@RequestBody RegularExpense newRegularExpense) {
        regularExpenseService.registRegularExpense(newRegularExpense);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyRegularExpenses(@RequestBody RegularExpense modifyRegularExpense) {
        regularExpenseService.modifyRegularExpense(modifyRegularExpense);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeRegularExpense(@RequestBody RegularExpense deleteRegularExpense) {
        regularExpenseService.deleteRegularExpense(deleteRegularExpense);

        return ResponseEntity
                .noContent()        // 204
                .build();
    }

}
