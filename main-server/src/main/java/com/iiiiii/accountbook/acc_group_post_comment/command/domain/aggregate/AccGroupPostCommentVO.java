package com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccGroupPostCommentVO {
    private int code;
    private String createdAt;
    private String detail;
    private int parentCode;
    private int accGroupPostCode;
    private int memberCode;
}
