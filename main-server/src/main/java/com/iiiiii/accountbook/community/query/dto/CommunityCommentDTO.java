package com.iiiiii.accountbook.community.query.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommunityCommentDTO {

    private int commentCode;
    private LocalDateTime createdAt;
    private String detail;
    private int communityPostCode;
    private int memberCode;
    private int parentCode;
}
