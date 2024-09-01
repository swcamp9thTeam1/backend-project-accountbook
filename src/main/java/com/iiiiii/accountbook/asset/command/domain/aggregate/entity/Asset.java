package com.iiiiii.accountbook.asset.command.domain.aggregate.entity;

import com.iiiiii.accountbook.common.AssetCategory;
import com.iiiiii.accountbook.common.YesOrNo;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "asset")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Asset {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;                               // 자산코드(PK)

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private AssetCategory category;                     // 자산카테고리

    @Column(name = "name")
    private String name;                                // 자산명

    @Column(name = "balance")
    private Long balance;                               // 현재잔액

    @Column(name = "payment_date")
    private Integer paymentDate;                        // 결제일

    @Column(name = "is_deleted")
    @Enumerated(EnumType.STRING)
    private YesOrNo isDeleted;                          // 삭제여부

    @Column(name = "member_code")
    private int memberCode;                             // 회원코드

    @PrePersist
    public void prePersist() {
        this.isDeleted = this.isDeleted == null ? YesOrNo.N : this.isDeleted;   // 삭제여부: default N
        this.balance = this.balance == null ? 0L : this.balance;                // 현재잔액: default 0
    }
}
