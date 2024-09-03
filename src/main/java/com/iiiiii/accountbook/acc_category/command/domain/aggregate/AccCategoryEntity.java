package com.iiiiii.accountbook.acc_category.command.domain.aggregate;

import com.iiiiii.accountbook.accbook.command.domain.aggregate.entity.Accbook;
import com.iiiiii.accountbook.common.InOrOut;
import com.iiiiii.accountbook.common.YesOrNo;
import com.iiiiii.accountbook.regular_expense.command.domain.aggregate.RegularExpenseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "AccCategoryEntity")
@Table(name = "acc_category")
public class AccCategoryEntity {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action = OnDeleteAction.CASCADE)
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
