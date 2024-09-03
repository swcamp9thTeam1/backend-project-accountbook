package com.iiiiii.accountbook.regular_expense.query.controller;

import com.iiiiii.accountbook.regular_expense.query.dto.RegularExpenseDTO;
import com.iiiiii.accountbook.regular_expense.query.dto.ResponseRegularExpenseDTO;
import com.iiiiii.accountbook.regular_expense.query.service.RegularExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("QueryRegularExpenseController")
@RequestMapping("/regular-expense")
public class RegularExpenseController {
    private RegularExpenseService regularExpenseService;

    @Autowired
    public RegularExpenseController(RegularExpenseService regularExpenseService) {
        this.regularExpenseService = regularExpenseService;
    }

    @GetMapping("/")
    public ResponseEntity<List<RegularExpenseDTO>> findAllRegularExpenses() {

        List<RegularExpenseDTO> regularExpenses = regularExpenseService.findAllRegularExpenses();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(regularExpenses);
    }

    @GetMapping("/{regularExpenseCode}")
    public ResponseEntity<RegularExpenseDTO> findOneRegularExpenses(@PathVariable("regularExpenseCode") int regularExpenseCode) {

        RegularExpenseDTO regularExpenses = regularExpenseService.findOneRegularExpenses(regularExpenseCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(regularExpenses);
    }

    @GetMapping("/members/{memberCode}")
    public ResponseEntity<List<ResponseRegularExpenseDTO>> findOneMemberRegularExpenses(@PathVariable("memberCode") int memberCode) {

        List<ResponseRegularExpenseDTO> regularExpenses = regularExpenseService.findOneMemberRegularExpenses(memberCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(regularExpenses);
    }
}
