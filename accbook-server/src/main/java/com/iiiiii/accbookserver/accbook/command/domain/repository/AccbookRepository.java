package com.iiiiii.accbookserver.accbook.command.domain.repository;

import com.iiiiii.accbookserver.accbook.command.domain.aggregate.entity.Accbook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccbookRepository extends JpaRepository<Accbook, Integer> {
        @Override
    Optional<Accbook> findById(Integer integer);
}