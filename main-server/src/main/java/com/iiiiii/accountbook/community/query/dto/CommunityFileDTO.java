package com.iiiiii.accountbook.community.query.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommunityFileDTO {

    private int fileCode;               // 첨부파일 코드
    private String name;                // 첨부파일명
    private String path;                // 파일경로
    private int communityPostCode;      // 파일이 첨부된 게시글 코드
}
