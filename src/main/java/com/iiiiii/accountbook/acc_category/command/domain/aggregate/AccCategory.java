package com.iiiiii.accountbook.acc_category.command.domain.aggregate;

public class AccCategory {
    private int code;
    private String name;
    private char financeType;
    private char visibility;
    private char isDeleted;
    private int memberCode;
    private int parentCode;

    public AccCategory() {
    }

    public AccCategory(String name, char financeType, char visibility, char isDeleted, int memberCode, int parentCode) {
        this.name = name;
        this.financeType = financeType;
        this.visibility = visibility;
        this.isDeleted = isDeleted;
        this.memberCode = memberCode;
        this.parentCode = parentCode;
    }

    public AccCategory(int code, String name, char financeType, char visibility, char isDeleted, int memberCode, int parentCode) {
        this.code = code;
        this.name = name;
        this.financeType = financeType;
        this.visibility = visibility;
        this.isDeleted = isDeleted;
        this.memberCode = memberCode;
        this.parentCode = parentCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getFinanceType() {
        return financeType;
    }

    public void setFinanceType(char financeType) {
        this.financeType = financeType;
    }

    public char getVisibility() {
        return visibility;
    }

    public void setVisibility(char visibility) {
        this.visibility = visibility;
    }

    public char getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(char isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public int getParentCode() {
        return parentCode;
    }

    public void setParentCode(int parentCode) {
        this.parentCode = parentCode;
    }

    @Override
    public String toString() {
        return "CommandAccCategoryDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", financeType=" + financeType +
                ", visibility=" + visibility +
                ", isDeleted=" + isDeleted +
                ", memberCode=" + memberCode +
                ", parentCode=" + parentCode +
                '}';
    }
}
