package com.iiiiii.accountbook.community.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "community_post_file")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommunityFile {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileCode;                   // 첨부파일 코드

    @Column(name = "name")
    private String name;                        // 파일명

    @Column(name = "path")
    private String path;                        // 파일경로

    @Column(name = "community_post_code")
    private Integer communityPostCode;          // 파일이 첨부된 게시글 코드
}
