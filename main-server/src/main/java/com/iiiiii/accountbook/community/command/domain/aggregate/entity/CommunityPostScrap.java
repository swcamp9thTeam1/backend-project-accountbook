package com.iiiiii.accountbook.community.command.domain.aggregate.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "community_post_scrap")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommunityPostScrap {

    @EmbeddedId
    private CommunityPostScrapId postScrapId;       // 복합키(회원코드 + 커뮤니티게시글코드)
}
