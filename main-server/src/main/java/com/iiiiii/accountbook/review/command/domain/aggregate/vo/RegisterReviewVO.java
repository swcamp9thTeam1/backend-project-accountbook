package com.iiiiii.accountbook.review.command.domain.aggregate.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class RegisterReviewVO {
    private int visitors;           // 인원수
    private Long totalExpense;
    private String oneLineReview;
    private int memberCode;         // 리뷰 작성자
    private int storeCode;          // 리뷰 남길 가게
}
