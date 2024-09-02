package com.iiiiii.accountbook.review.query.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class selectStoreReviewByMemberOrStoreCodeDTO {
    private Integer code;
    private String storeName;
    private String memberNickname;
    private String createdAt;
    private Integer visitors;
    private Long totalExpense;
    private String oneLineReview;
}
