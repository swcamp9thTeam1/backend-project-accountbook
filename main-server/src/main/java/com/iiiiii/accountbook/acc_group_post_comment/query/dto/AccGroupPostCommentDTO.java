package com.iiiiii.accountbook.acc_group_post_comment.query.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AccGroupPostCommentDTO {
    private int code;
    private LocalDateTime createdAt;
    private String detail;
    private int parentCode;
    private int accGroupPostCode;
    private int memberCode;

    public AccGroupPostCommentDTO(String detail, int parentCode, int accGroupPostCode, int memberCode) {
        this.detail = detail;
        this.parentCode = parentCode;
        this.accGroupPostCode = accGroupPostCode;
        this.memberCode = memberCode;
    }

    public AccGroupPostCommentDTO(int code, LocalDateTime createdAt, String detail, int parentCode, int accGroupPostCode, int memberCode) {
        this.code = code;
        this.createdAt = createdAt;
        this.detail = detail;
        this.parentCode = parentCode;
        this.accGroupPostCode = accGroupPostCode;
        this.memberCode = memberCode;
    }
}
