package com.iiiiii.accountbook.community.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "community_post_scrap")
@IdClass(CommunityPostScrapId.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommunityPostScrap {

    @Id
    @Column(name = "member_code")
    private Integer memberCode;             // 회원 코드

    @Id
    @Column(name = "community_post_code")
    private Integer communityPostCode;      // 커뮤니티 게시글 코드
}
