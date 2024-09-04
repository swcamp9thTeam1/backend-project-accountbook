package com.iiiiii.accountbook.acc_group_post.command.domain.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AccGroupPost {
    private int code;
    private String createdAt;
    private String title;
    private String detail;
    private int memberCode;
    private int accGroupCode;

    public AccGroupPost(String title, String detail, int memberCode, int accGroupCode) {
        this.title = title;
        this.detail = detail;
        this.memberCode = memberCode;
        this.accGroupCode = accGroupCode;
    }

    public AccGroupPost(int code, String createdAt, String title, String detail, int memberCode, int accGroupCode) {
        this.code = code;
        this.createdAt = createdAt;
        this.title = title;
        this.detail = detail;
        this.memberCode = memberCode;
        this.accGroupCode = accGroupCode;
    }
}
