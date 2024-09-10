package com.iiiiii.accbookserver.accbook.query.dto;

import com.iiiiii.accbookserver.common.InOrOutOrTransfer;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccbookCategoryStatsDTO {
    private String CategoryName;
    private InOrOutOrTransfer financeType;
    private Long totalSpent;
}
