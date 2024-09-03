package com.iiiiii.accountbook.acc_group_post.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "command_acc_group_post_entity")
@Table(name = "acc_group_post")
public class AccGroupPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private int code;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "title")
    private String title;

    @Column(name = "detail")
    private String detail;

    @Column(name = "member_code")
    private int memberCode;

    @Column(name = "acc_group_code")
    private int accGroupCode;
}
