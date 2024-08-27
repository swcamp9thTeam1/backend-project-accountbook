package com.iiiiii.accountbook.accbook.query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccbookTop3CategoryDTO {
    private Integer memberCode;
    private String monthYear;
    private String categoryName;
    private Long totalSpent;
    private Integer spendingRank;
}
