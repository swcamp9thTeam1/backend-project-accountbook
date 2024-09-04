package com.iiiiii.accountbook.acc_category.query.dto;

import com.iiiiii.accountbook.common.InOrOut;
import com.iiiiii.accountbook.common.YesOrNo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AccCategoryDTO {
    private int code;
    private String name;
    private InOrOut financeType;
    private YesOrNo visibility;
    private YesOrNo isDeleted;
    private int memberCode;
    private int parentCode;

    public AccCategoryDTO(String name, InOrOut financeType, YesOrNo visibility, YesOrNo isDeleted, int memberCode, int parentCode) {
        this.name = name;
        this.financeType = financeType;
        this.visibility = visibility;
        this.isDeleted = isDeleted;
        this.memberCode = memberCode;
        this.parentCode = parentCode;
    }

    public AccCategoryDTO(String name, InOrOut financeType, YesOrNo visibility, YesOrNo isDeleted, int memberCode) {
        this.name = name;
        this.financeType = financeType;
        this.visibility = visibility;
        this.isDeleted = isDeleted;
        this.memberCode = memberCode;
    }

    public AccCategoryDTO(int code, String name, InOrOut financeType, YesOrNo visibility, YesOrNo isDeleted, int memberCode, int parentCode) {
        this.code = code;
        this.name = name;
        this.financeType = financeType;
        this.visibility = visibility;
        this.isDeleted = isDeleted;
        this.memberCode = memberCode;
        this.parentCode = parentCode;
    }
}
