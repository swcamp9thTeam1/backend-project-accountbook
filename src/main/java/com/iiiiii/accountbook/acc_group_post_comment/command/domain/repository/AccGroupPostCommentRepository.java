package com.iiiiii.accountbook.acc_group_post_comment.command.domain.repository;

import com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate.AccGroupPostCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccGroupPostCommentRepository extends JpaRepository<AccGroupPostCommentEntity, Integer> {
}
