package com.iiiiii.accountbook.regular_expense.command.controller;

import com.iiiiii.accountbook.regular_expense.command.dto.RegularExpenseDTO;
import com.iiiiii.accountbook.regular_expense.command.service.RegularExpenseService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController("commandController")
@RequestMapping("/regular-expenses")
public class RegularExpenseController {
    private RegularExpenseService regularExpenseService;

    @Autowired
    public RegularExpenseController(RegularExpenseService regularExpenseService) {
        this.regularExpenseService = regularExpenseService;
    }

    @GetMapping("/regist")
    public void registRegularExpenses() {}

    @PostMapping("/regist")
    public ResponseEntity<?> registRegularExpenses(@RequestBody RegularExpenseDTO newRegularExpense) {
        int result = regularExpenseService.registRegularExpense(newRegularExpense);

        return ResponseEntity
                .created(URI.create("/regular-expenses/" + newRegularExpense.getMemberCode()))
                .build();
    }

    @DeleteMapping("/delete/{regularExpenseCode}")
    public ResponseEntity<?> removeRegularExpense(@PathVariable("regularExpenseCode") int regularExpenseCode) {
        int result = regularExpenseService.deleteRegularExpense(regularExpenseCode);

        return ResponseEntity
                .noContent()        // 204
                .build();
    }

}
