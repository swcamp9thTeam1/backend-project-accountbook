package com.iiiiii.accountbook.regular_expense.query.dto;

import lombok.Data;

@Data
public class RegularExpenseDTO {
    private int code;
    private int expenseDate;
    private String name;
    private long amount;
    private int memberCode;
    private int assetCode;
    private int accCategoryCode;

    public RegularExpenseDTO(int code, int expenseDate, String name, int amount, int memberCode, int assetCode, int accCategoryCode) {
        this.code = code;
        this.expenseDate = expenseDate;
        this.name = name;
        this.amount = amount;
        this.memberCode = memberCode;
        this.assetCode = assetCode;
        this.accCategoryCode = accCategoryCode;
    }
}
