package com.iiiiii.accountbook.accbook.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "acc_comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccComment {
    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accCommentCode;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "parent_code", nullable = true)
    private Integer parentCode;

    @Column(name = "accbook_code", nullable = false)
    private Integer accbookCode;

    @Column(name = "member_code", nullable = false)
    private Integer memberCode;
}
