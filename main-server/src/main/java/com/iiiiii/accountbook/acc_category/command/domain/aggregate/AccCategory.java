package com.iiiiii.accountbook.acc_category.command.domain.aggregate;

import com.iiiiii.accountbook.common.InOrOut;
import com.iiiiii.accountbook.common.YesOrNo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccCategory {
    private int code;
    private String name;
    private InOrOut financeType;
    private YesOrNo visibility;
    private YesOrNo isDeleted;
    private int memberCode;
    private Integer parentCode;

    public AccCategory(String name, InOrOut financeType, YesOrNo visibility, YesOrNo isDeleted, int memberCode, Integer parentCode) {
        this.name = name;
        this.financeType = financeType;
        this.visibility = visibility;
        this.isDeleted = isDeleted;
        this.memberCode = memberCode;
        this.parentCode = parentCode;
    }
    public AccCategory(String name, InOrOut financeType, YesOrNo visibility, YesOrNo isDeleted, int memberCode) {
        this.name = name;
        this.financeType = financeType;
        this.visibility = visibility;
        this.isDeleted = isDeleted;
        this.memberCode = memberCode;
    }

    public AccCategory(int code, String name, InOrOut financeType, YesOrNo visibility, YesOrNo isDeleted, int memberCode, Integer parentCode) {
        this.code = code;
        this.name = name;
        this.financeType = financeType;
        this.visibility = visibility;
        this.isDeleted = isDeleted;
        this.memberCode = memberCode;
        this.parentCode = parentCode;
    }
}
