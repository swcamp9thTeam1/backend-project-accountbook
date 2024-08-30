package com.iiiiii.accountbook.regular_expense.query.dto;

import lombok.Data;

@Data
public class ResponseRegularExpenseDTO {
    private int code;
    private int expenseDate;
    private String name;
    private int amount;
    private int assetCode;
    private int accCategoryCode;
}
