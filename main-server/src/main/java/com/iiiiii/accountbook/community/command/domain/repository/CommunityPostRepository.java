package com.iiiiii.accountbook.community.command.domain.repository;

import com.iiiiii.accountbook.community.command.domain.aggregate.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Integer> {
}
