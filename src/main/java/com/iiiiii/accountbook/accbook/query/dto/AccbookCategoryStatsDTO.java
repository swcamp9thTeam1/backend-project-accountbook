package com.iiiiii.accountbook.accbook.query.dto;

import com.iiiiii.accountbook.common.InOrOut;
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
