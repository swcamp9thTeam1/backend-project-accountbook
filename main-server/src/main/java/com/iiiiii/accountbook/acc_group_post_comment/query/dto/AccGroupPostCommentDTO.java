package com.iiiiii.accountbook.acc_group_post_comment.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccGroupPostCommentDTO {
    private int code;
    private LocalDateTime createdAt;
    private String detail;
    private int parentCode;
    private int accGroupPostCode;
    private int memberCode;
}
