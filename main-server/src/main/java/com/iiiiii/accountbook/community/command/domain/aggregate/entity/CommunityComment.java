package com.iiiiii.accountbook.community.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "community_post_comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommunityComment {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentCode;                    // 댓글코드

    @Column(name = "created_at")
    private String commentCreatedAt;            // 댓글 작성일시

    @Column(name = "detail")
    private String commentDetail;               // 댓글 내용

    @Column(name = "community_post_code")
    private Integer communityPostCode;          // 댓글이 등록된 게시글 코드

    @Column(name = "member_code")
    private int memberCode;                     // 댓글 작성 회원 코드

    @Column(name = "parent_code")
    private Integer parentCode;                 // 상위댓글 코드(대댓글의 경우)
}
