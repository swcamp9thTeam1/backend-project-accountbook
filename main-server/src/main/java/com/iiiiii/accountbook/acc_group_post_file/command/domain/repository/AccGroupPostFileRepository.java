package com.iiiiii.accountbook.acc_group_post_file.command.domain.repository;

import com.iiiiii.accountbook.acc_group_post_file.command.domain.aggregate.AccGroupPostFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccGroupPostFileRepository extends JpaRepository<AccGroupPostFileEntity, Integer> {
}
