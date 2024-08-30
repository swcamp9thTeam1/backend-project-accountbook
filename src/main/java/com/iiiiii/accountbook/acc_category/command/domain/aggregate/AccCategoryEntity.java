package com.iiiiii.accountbook.acc_category.command.domain.aggregate;

import com.iiiiii.accountbook.common.InOrOut;
import com.iiiiii.accountbook.common.YesOrNo;
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
    @Enumerated(EnumType.STRING)
    private InOrOut financeType;

    @Column(name = "visibility")
    @Enumerated(EnumType.STRING)
    private YesOrNo visibility;

    @Column(name = "is_deleted")
    @Enumerated(EnumType.STRING)
    private YesOrNo isDeleted;

    @Column(name = "member_code")
    private int memberCode;

    @Column(name = "parent_code", nullable = true)
    private int parentCode;

}
