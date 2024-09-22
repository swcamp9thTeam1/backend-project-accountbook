package com.iiiiii.accbookserver.accbook.query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccbookMonthlyEvalDTO {
    private String dailyDate;
    private Integer totalOAmount;
    private Integer totalIAmount;
    private String evaluation;      // Good, Bad
}
