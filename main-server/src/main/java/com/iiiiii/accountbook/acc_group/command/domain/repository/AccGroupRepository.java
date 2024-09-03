package com.iiiiii.accountbook.acc_group.command.domain.repository;

import com.iiiiii.accountbook.acc_group.command.domain.aggregate.AccGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccGroupRepository extends JpaRepository<AccGroupEntity, Integer> {
}
