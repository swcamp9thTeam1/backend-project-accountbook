package com.iiiiii.accountbook.regular_expense.command.application.controller;

import com.iiiiii.accountbook.regular_expense.command.application.service.RegularExpenseService;
import com.iiiiii.accountbook.regular_expense.command.domain.aggregate.RegularExpense;
import com.iiiiii.accountbook.regular_expense.command.domain.aggregate.RegularExpenseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("CommandRegularExpenseController")
@RequestMapping("/regular-expense")
public class RegularExpenseController {
    private RegularExpenseService regularExpenseService;

    @Autowired
    public RegularExpenseController(RegularExpenseService regularExpenseService) {
        this.regularExpenseService = regularExpenseService;
    }

    @PostMapping("/regist")
    public ResponseEntity<RegularExpenseEntity> registRegularExpenses(@RequestBody RegularExpense newRegularExpense) {
        RegularExpenseEntity result = regularExpenseService.registRegularExpense(newRegularExpense);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PostMapping("/modify")
    public ResponseEntity<RegularExpenseEntity> modifyRegularExpenses(@RequestBody RegularExpense modifyRegularExpense) {
        RegularExpenseEntity result = regularExpenseService.modifyRegularExpense(modifyRegularExpense);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeRegularExpense(@RequestBody RegularExpense deleteRegularExpense) {
        regularExpenseService.deleteRegularExpense(deleteRegularExpense);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
