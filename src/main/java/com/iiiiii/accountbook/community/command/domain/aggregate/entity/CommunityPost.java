package com.iiiiii.accountbook.community.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "community_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommunityPost {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;           // 게시글 코드

    @Column(name = "created_at")
    private String createdAt;       // 작성일시

    @Column(name = "title")
    private String title;           // 제목

    @Column(name = "detail")
    private String detail;          // 내용

    @Column(name = "member_code")
    private int memberCode;         // 작성자(회원 코드)
}
