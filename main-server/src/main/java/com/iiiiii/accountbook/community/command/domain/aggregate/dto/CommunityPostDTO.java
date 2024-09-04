package com.iiiiii.accountbook.community.command.domain.aggregate.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommunityPostDTO {

    private int postCode;               // 게시글 코드
    private LocalDateTime createdAt;    // 작성일시
    private String title;               // 제목
    private String detail;              // 내용
    private int memberCode;             // 작성자

    public CommunityPostDTO(LocalDateTime createdAt, String title, String detail, int memberCode) {
        this.createdAt = createdAt;
        this.title = title;
        this.detail = detail;
        this.memberCode = memberCode;
    }
}
