package com.iiiiii.accountbook.acc_category.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "AccCategoryEntity")
@Table(name = "acc_category")
public class AccCategoryEntity {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @Column(name = "name")
    private String name;

    @Column(name = "finance_type")
    private char financeType;

    @Column(name = "visibility")
    private char visibility;

    @Column(name = "is_deleted")
    private char isDeleted;

    @Column(name = "member_code")
    private int memberCode;

    @Column(name = "parent_code", nullable = true)
    private int parentCode;

}
