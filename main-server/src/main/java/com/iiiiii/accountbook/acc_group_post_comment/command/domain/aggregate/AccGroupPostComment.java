package com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class AccGroupPostComment {
    private int code;
    private LocalDateTime createdAt;
    private String detail;
    private Integer parentCode;
    private int accGroupPostCode;
    private int memberCode;

    public AccGroupPostComment(String detail, Integer parentCode, int accGroupPostCode, int memberCode) {
        this.detail = detail;
        this.parentCode = parentCode;
        this.accGroupPostCode = accGroupPostCode;
        this.memberCode = memberCode;
    }
    public AccGroupPostComment(int code, LocalDateTime createdAt, String detail, Integer parentCode, int accGroupPostCode, int memberCode) {
        this.code = code;
        this.createdAt = createdAt;
        this.detail = detail;
        this.parentCode = parentCode;
        this.accGroupPostCode = accGroupPostCode;
        this.memberCode = memberCode;
    }
}
