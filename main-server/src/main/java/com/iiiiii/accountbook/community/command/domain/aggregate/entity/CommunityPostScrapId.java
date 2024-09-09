package com.iiiiii.accountbook.community.command.domain.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Table(name = "community_post_scrap")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CommunityPostScrapId implements Serializable {

    @Column(name = "member_code")
    private Integer memberCode;             // 회원 코드

    @Column(name = "community_post_code")
    private Integer communityPostCode;      // 커뮤니티 게시글 코드
}
