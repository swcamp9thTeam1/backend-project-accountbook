package com.iiiiii.accbookserver.accbook.command.domain.aggregate.dto;

import com.iiiiii.accbookserver.common.InOrOutOrTransfer;
import com.iiiiii.accbookserver.common.YesOrNo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccbookDTO {
    private String createdAt;
    private String title;
    private Long amount;
    private YesOrNo isRegular;
    private Integer memberCode;
    private Integer accCategoryCode;
    private Integer storeCode;
    private Integer assetCode;
    private InOrOutOrTransfer financeType;
}
