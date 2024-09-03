package com.iiiiii.accountbook.regular_expense.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRegularExpenseDTO {
    private int code;
    private int expenseDate;
    private String name;
    private long amount;
    private int assetCode;
    private int accCategoryCode;
}
