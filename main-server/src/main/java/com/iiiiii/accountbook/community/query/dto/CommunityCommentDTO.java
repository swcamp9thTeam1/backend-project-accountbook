package com.iiiiii.accountbook.community.query.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommunityCommentDTO {

    private int commentCode;                // 댓글 코드

    private LocalDateTime createdAt;        // 댓글 작성일시

    private String detail;                  // 내용

    private int communityPostCode;          // 댓글이 작성된 게시글 코드

    private int memberCode;                 // 작성자(회원 코드)

    private int parentCode;                 // 상위댓글 코드(대댓글의 경우)
}
