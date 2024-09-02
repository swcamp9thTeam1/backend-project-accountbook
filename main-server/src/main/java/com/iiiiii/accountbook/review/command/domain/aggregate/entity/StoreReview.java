package com.iiiiii.accountbook.review.command.domain.aggregate.entity;


import jakarta.persistence.*;
import lombok.*;


// 주석. JPA 엔티티 정의
@Entity
@Table(name = "store_review")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StoreReview {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeReviewCode;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "visitors", nullable = false)
    private Integer visitors;

    @Column(name = "total_expense", nullable = false)
    private Long totalExpense;

    @Column(name = "one_line_review", nullable = false)
    private String oneLineReview;

    @Column(name = "member_code", nullable = false)
    private Integer memberCode;

    @Column(name = "store_code", nullable = false)
    private Integer storeCode;

}
