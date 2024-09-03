package com.iiiiii.accountbook.regular_expense.command.domain.aggregate;

import com.iiiiii.accountbook.acc_category.command.domain.aggregate.AccCategoryEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "command_regularExpense")
@Table(name = "regular_expense")
public class RegularExpenseEntity {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @Column(name = "expense_date", nullable = true)
    private int expenseDate;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private int amount;

    @Column(name = "member_code")
    private int memberCode;

    @Column(name = "asset_code")
    private int assetCode;

    @Column(name = "acc_category_code")
    private int accCategoryCode;
}
