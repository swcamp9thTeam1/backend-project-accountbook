package com.iiiiii.accountbook.community.command.domain.aggregate.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommunityPostScrapId implements Serializable {

    private Integer memberCode;             // 회원 코드
    private Integer communityPostCode;      // 커뮤니티 게시글 코드
}
