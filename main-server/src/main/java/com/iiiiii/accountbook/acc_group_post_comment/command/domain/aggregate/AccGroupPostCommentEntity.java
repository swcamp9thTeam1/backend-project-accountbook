package com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "AccGroupPostCommentEntity")
@Table(name = "acc_group_post_comment")
public class AccGroupPostCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private int code;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "detail")
    private String detail;

    @Column(name = "acc_group_post_code")
    private int accGroupPostCode;

    @Column(name = "member_code")
    private int memberCode;

    @Column(name = "parent_code", nullable = true)
    private Integer parentCode;

    @Column(name = "acc_group_code")
    private int accGroupCode;
}
