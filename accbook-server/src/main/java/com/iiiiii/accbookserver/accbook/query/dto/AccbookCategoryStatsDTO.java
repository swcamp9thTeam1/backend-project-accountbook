package com.iiiiii.accbookserver.accbook.query.dto;

import com.iiiiii.accbookserver.common.InOrOut;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccbookCategoryStatsDTO {
    private String CategoryName;
    private InOrOut financeType;
    private Long totalSpent;
}
