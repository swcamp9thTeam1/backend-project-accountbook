package com.iiiiii.accountbook.acc_group.command.domain.repository;

import com.iiiiii.accountbook.acc_group.command.domain.aggregate.CommandAccGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandAccGroupRepository extends JpaRepository<CommandAccGroupEntity, Integer> {
}
