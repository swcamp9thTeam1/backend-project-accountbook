package com.iiiiii.accountbook.community.command.domain.aggregate.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommunityCommentDTO {

    private Integer commentCode;                // 댓글코드
    private LocalDateTime commentCreatedAt;     // 댓글 작성일시
    private String commentDetail;               // 댓글 내용
    private Integer communityPostCode;          // 댓글이 등록된 게시글 코드
    private int memberCode;                     // 댓글 작성 회원 코드
    private Integer parentCode;                 // 상위 댓글 코드(대댓글의 경우)

    public CommunityCommentDTO(LocalDateTime commentCreatedAt, String commentDetail, Integer communityPostCode, int memberCode, Integer parentCode) {
        this.commentCreatedAt = commentCreatedAt;
        this.commentDetail = commentDetail;
        this.communityPostCode = communityPostCode;
        this.memberCode = memberCode;
        this.parentCode = parentCode;
    }
}
