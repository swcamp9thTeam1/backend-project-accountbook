package com.iiiiii.accountbook.accbook.command.domain.repository;

import com.iiiiii.accountbook.accbook.command.domain.aggregate.entity.AccComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccCommentRepository extends JpaRepository<AccComment, Integer> {
}
