package com.iiiiii.accountbook.acc_category.command.domain.repository;

import com.iiiiii.accountbook.acc_category.command.domain.aggregate.AccCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandAccCategoryRepository extends JpaRepository<AccCategoryEntity, Integer> {

}
