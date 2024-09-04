package com.iiiiii.accountbook.acc_group_post.query.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class AccGroupPostDTO {
    private int code;
    private LocalDateTime createdAt;
    private String title;
    private String detail;
    private int memberCode;
    private int accGroupCode;

    public AccGroupPostDTO(String title, String detail, int memberCode, int accGroupCode) {
        this.title = title;
        this.detail = detail;
        this.memberCode = memberCode;
        this.accGroupCode = accGroupCode;
    }

    public AccGroupPostDTO(int code, LocalDateTime createdAt, String title, String detail, int memberCode, int accGroupCode) {
        this.code = code;
        this.createdAt = createdAt;
        this.title = title;
        this.detail = detail;
        this.memberCode = memberCode;
        this.accGroupCode = accGroupCode;
    }
}
