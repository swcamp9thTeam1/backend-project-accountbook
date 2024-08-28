package com.iiiiii.accountbook.accbook.command.domain.repository;

import com.iiiiii.accountbook.accbook.command.domain.aggregate.entity.Accbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccbookRepository extends JpaRepository<Accbook, Integer> {
}