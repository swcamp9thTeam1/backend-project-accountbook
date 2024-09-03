package com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseRegExpVO {
    private int code;
    private int expenseDate;
    private String name;
    private int amount;
    private int memberCode;
    private int assetCode;
    private int accCategoryCode;
}
