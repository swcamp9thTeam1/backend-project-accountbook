package com.iiiiii.accountbook.review.command.domain.aggregate.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StoreReviewDTO {
    private String createdAt;
    private Integer visitors;
    private Long totalExpense;
    private String oneLineReview;
    private Integer memberCode;
    private Integer storeCode;

}
