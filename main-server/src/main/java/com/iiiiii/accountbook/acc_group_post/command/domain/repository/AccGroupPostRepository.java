package com.iiiiii.accountbook.acc_group_post.command.domain.repository;

import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.AccGroupPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccGroupPostRepository extends JpaRepository<AccGroupPostEntity, Integer> {
}
