package com.iiiiii.accountbook.accbook.command.domain.aggregate.entity;

import com.iiiiii.accountbook.common.YesOrNo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accbook")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Accbook {
    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accbookCode;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "is_regular", nullable = false)
    @Enumerated(EnumType.STRING)
    private YesOrNo isRegular;

    @Column(name = "member_code", nullable = false)
    private Integer memberCode;

    @Column(name = "acc_category_code", nullable = false)
    private Integer accCategoryCode;

    @Column(name = "store_code", nullable = true)
    private Integer storeCode;

    @Column(name = "asset_code", nullable = false)
    private Integer assetCode;


}
