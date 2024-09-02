package com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class RegisterAccGroupPostVO {

    private String postTitle;
    private String postDetail;
    private int memberCode;         // 작성자
}
