package com.iiiiii.accountbook.accbook.query.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccCommentDTO {
    private String detail;
    private String createdAt;
    private Integer parentCode;
    private Integer memberCode;     // 댓글 작성자
}