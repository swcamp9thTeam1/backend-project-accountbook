package com.iiiiii.accbookserver.accbook.query.dto;

import com.iiiiii.accbookserver.common.InOrOutOrTransfer;
import com.iiiiii.accbookserver.common.YesOrNo;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccbookDTO {
    private int accbookCode;
    private LocalDateTime createdAt;
    private Long amount;
    private YesOrNo isRegular;
    private String title;
    private Integer memberCode;
    private Integer accCategoryCode;
    private Integer storeCode;
    private Integer assetCode;
    private InOrOutOrTransfer financeType;
    private Integer inAssetCode;
}
