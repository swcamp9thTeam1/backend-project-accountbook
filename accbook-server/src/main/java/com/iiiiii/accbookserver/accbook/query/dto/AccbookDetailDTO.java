package com.iiiiii.accbookserver.accbook.query.dto;

import com.iiiiii.accbookserver.common.InOrOutOrTransfer;
import com.iiiiii.accbookserver.common.YesOrNo;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AccbookDetailDTO {
    private int accbookCode;
    private String createdAt;
    private Long amount;
    private YesOrNo isRegular;
    private String title;
    private String accCategoryName;
    private String storeName;
    private String assetName;
    private InOrOutOrTransfer financeType;
    private List<AccCommentDTO> accCommentDTOList;  // 댓글 목록
}
