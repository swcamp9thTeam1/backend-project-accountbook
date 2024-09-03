package com.iiiiii.accountbook.acc_group_post.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccGroupPostDTO {
    private int code;
    private java.time.LocalDateTime createdAt;
    private String title;
    private String detail;
    private int memberCode;
    private int accGroupCode;
}
