package com.iiiiii.accbookserver.accbook.command.domain.aggregate.entity;

import com.iiiiii.accbookserver.common.InOrOutOrTransfer;
import com.iiiiii.accbookserver.common.YesOrNo;
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

    @Column(name = "acc_category_code", nullable = true) // financy_type이 '이체'인 경우에는 카테고리 null
    private Integer accCategoryCode;

    @Column(name = "store_code", nullable = true)
    private Integer storeCode;

    @Column(name = "asset_code", nullable = false)
    private Integer assetCode;

    @Column(name = "financy_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private InOrOutOrTransfer financyType;
}
