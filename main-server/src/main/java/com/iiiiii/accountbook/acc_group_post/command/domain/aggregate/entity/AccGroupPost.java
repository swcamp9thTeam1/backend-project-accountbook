package com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "acc_group_post")
@Getter
@ToString
public class AccGroupPost {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @Column(name = "created_at", nullable = false)
    @Setter
    private String createdAt;

    @Column(name = "title", nullable = false)
    @Setter
    private String title;

    @Column(name = "detail", nullable = false)
    @Setter
    private String detail;

    @Column(name = "member_code", nullable = false)
    @Setter
    private int memberCode;

    @Column(name = "acc_group_code", nullable = false)
    @Setter
    private int accGroupCode;
}
