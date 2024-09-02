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
    private Integer commentCode;    // 댓글 코드
    private Integer parentCode;
    private Integer memberCode;     // 댓글 작성자
}