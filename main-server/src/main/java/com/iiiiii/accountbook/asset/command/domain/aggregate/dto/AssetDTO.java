package com.iiiiii.accountbook.asset.command.domain.aggregate.dto;

import com.iiiiii.accountbook.common.AssetCategory;
import com.iiiiii.accountbook.common.YesOrNo;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AssetDTO {

    private int code;                   // 자산코드
    private AssetCategory category;     // 자산카테고리
    private String name;                // 자산명
    private Long balance;               // 현재잔액
    private Integer paymentDate;        // 결제일
    private YesOrNo isDeleted;          // 삭제여부
    private int memberCode;             // 회원코드
    private Integer relatedAssetCode;   // 연동된 자산코드(체크카드의 경우)

    public AssetDTO(AssetCategory category, String name, Long balance, int memberCode) {
        this.category = category;
        this.name = name;
        this.balance = balance;
        this.memberCode = memberCode;
    }

    public AssetDTO(AssetCategory category, String name, Long balance, int memberCode, Integer relatedAssetCode) {
        this.category = category;
        this.name = name;
        this.balance = balance;
        this.memberCode = memberCode;
        this.relatedAssetCode = relatedAssetCode;
    }
}
