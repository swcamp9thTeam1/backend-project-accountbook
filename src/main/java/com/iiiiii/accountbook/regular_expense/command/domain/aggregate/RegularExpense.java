package com.iiiiii.accountbook.regular_expense.command.domain.aggregate;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegularExpense {
    private int code;
    private int expenseDate;
    private String name;
    private int amount;
    private int memberCode;
    private int assetCode;
    private int accCategoryCode;

    public RegularExpense(int expenseDate, String name, int amount, int memberCode, int assetCode, int accCategoryCode) {
        this.expenseDate = expenseDate;
        this.name = name;
        this.amount = amount;
        this.memberCode = memberCode;
        this.assetCode = assetCode;
        this.accCategoryCode = accCategoryCode;
    }
}
