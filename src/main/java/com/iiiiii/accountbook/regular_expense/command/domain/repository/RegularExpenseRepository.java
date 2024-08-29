package com.iiiiii.accountbook.regular_expense.command.domain.repository;

import com.iiiiii.accountbook.regular_expense.command.domain.aggregate.RegularExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegularExpenseRepository extends JpaRepository<RegularExpenseEntity, Long> {

}
