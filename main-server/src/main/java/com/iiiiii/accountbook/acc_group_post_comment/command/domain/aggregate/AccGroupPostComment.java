package com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AccGroupPostComment {
    private int code;
    private String createdAt;
    private String detail;
    private int parentCode;
    private int accGroupPostCode;
    private int memberCode;

    public AccGroupPostComment(String detail, int parentCode, int accGroupPostCode, int memberCode) {
        this.detail = detail;
        this.parentCode = parentCode;
        this.accGroupPostCode = accGroupPostCode;
        this.memberCode = memberCode;
    }

    public AccGroupPostComment(int code, String createdAt, String detail, int parentCode, int accGroupPostCode, int memberCode) {
        this.code = code;
        this.createdAt = createdAt;
        this.detail = detail;
        this.parentCode = parentCode;
        this.accGroupPostCode = accGroupPostCode;
        this.memberCode = memberCode;
    }
}
