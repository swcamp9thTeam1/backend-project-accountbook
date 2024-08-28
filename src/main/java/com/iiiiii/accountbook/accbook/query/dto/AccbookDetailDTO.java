package com.iiiiii.accountbook.accbook.query.dto;

import com.iiiiii.accountbook.common.YesOrNo;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccbookDetailDTO {
    private int accbookCode;
    private String createdAt;
    private Long amount;
    private YesOrNo isRegular;
    private String title;
    private String accCategoryName;
    private String storeName;
    private String assetName;
    private List<AccCommentDTO> accCommentDTOList;  // 댓글 목록
}
