package com.iiiiii.accountbook.community.command.domain.aggregate.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommunityPostScrapDTO {

    private Integer memberCode;             // 회원 코드
    private Integer communityPostCode;      // 커뮤니티 게시글 코드
}
