package com.iiiiii.accountbook.community.command.domain.aggregate.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommnunityFileDTO {

    private Integer fileCode;                   // 첨부파일 코드
    private String name;                        // 파일명
    private String path;                        // 파일경로
    private Integer communityPostCode;          // 첨부된 게시글 코드

    public CommnunityFileDTO(String name, String path, Integer communityPostCode) {
        this.name = name;
        this.path = path;
        this.communityPostCode = communityPostCode;
    }
}
