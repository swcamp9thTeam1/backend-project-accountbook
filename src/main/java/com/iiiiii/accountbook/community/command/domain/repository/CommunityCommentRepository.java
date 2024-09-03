package com.iiiiii.accountbook.community.command.domain.repository;

import com.iiiiii.accountbook.community.command.domain.aggregate.entity.CommunityComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Integer> {
}
