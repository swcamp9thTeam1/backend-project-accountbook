package com.iiiiii.accountbook.regular_expense.query.dto;

public class RegularExpenseDTO {
    private int code;
    private int expenseDate;
    private String name;
    private int amount;
    private int memberCode;
    private int assetCode;
    private int accCategoryCode;

    public RegularExpenseDTO() {
    }

    public RegularExpenseDTO(int expenseDate, String name, int amount, int memberCode, int assetCode, int accCategoryCode) {
        this.expenseDate = expenseDate;
        this.name = name;
        this.amount = amount;
        this.memberCode = memberCode;
        this.assetCode = assetCode;
        this.accCategoryCode = accCategoryCode;
    }

    public RegularExpenseDTO(int code, int expenseDate, String name, int amount, int memberCode, int assetCode, int accCategoryCode) {
        this.code = code;
        this.expenseDate = expenseDate;
        this.name = name;
        this.amount = amount;
        this.memberCode = memberCode;
        this.assetCode = assetCode;
        this.accCategoryCode = accCategoryCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(int expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMember_code(int memberCode) {
        this.memberCode = memberCode;
    }

    public int getAssetCode() {
        return assetCode;
    }

    public void setAsset_code(int assetCode) {
        this.assetCode = assetCode;
    }

    public int getAccCategoryCode() {
        return accCategoryCode;
    }

    public void setAccCategoryCode(int accCategoryCode) {
        this.accCategoryCode = accCategoryCode;
    }

    @Override
    public String toString() {
        return "RegularExpenseDTO{" +
                "code=" + code +
                ", expenseDate=" + expenseDate +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", memberCode=" + memberCode +
                ", assetCode=" + assetCode +
                ", accCategoryCode=" + accCategoryCode +
                '}';
    }
}
