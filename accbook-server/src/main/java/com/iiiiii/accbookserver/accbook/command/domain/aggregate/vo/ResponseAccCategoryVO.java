package com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo;

import com.iiiiii.accbookserver.common.InOrOut;
import com.iiiiii.accbookserver.common.InOrOutOrTransfer;
import com.iiiiii.accbookserver.common.YesOrNo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseAccCategoryVO {
    private int code;
    private String name;
    private InOrOutOrTransfer financeType;
    private YesOrNo visibility;
    private YesOrNo isDeleted;
    private int memberCode;
    private int parentCode;

//    public ResponseAccCategoryVO(String name, InOrOut financeType, YesOrNo visibility, YesOrNo isDeleted, int memberCode, int parentCode) {
//        this.name = name;
//        this.financeType = financeType;
//        this.visibility = visibility;
//        this.isDeleted = isDeleted;
//        this.memberCode = memberCode;
//        this.parentCode = parentCode;
//    }
}
