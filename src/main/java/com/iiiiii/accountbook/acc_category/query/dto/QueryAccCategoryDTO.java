package com.iiiiii.accountbook.acc_category.query.dto;

import com.iiiiii.accountbook.common.InOrOut;
import com.iiiiii.accountbook.common.YesOrNo;
import lombok.Data;

@Data
public class QueryAccCategoryDTO {
    private int code;
    private String name;
    private InOrOut financeType;
    private YesOrNo visibility;
    private YesOrNo isDeleted;
    private int memberCode;
    private int parentCode;

    public QueryAccCategoryDTO(String name, InOrOut financeType, YesOrNo visibility, YesOrNo isDeleted, int memberCode, int parentCode) {
        this.name = name;
        this.financeType = financeType;
        this.visibility = visibility;
        this.isDeleted = isDeleted;
        this.memberCode = memberCode;
        this.parentCode = parentCode;
    }
}
