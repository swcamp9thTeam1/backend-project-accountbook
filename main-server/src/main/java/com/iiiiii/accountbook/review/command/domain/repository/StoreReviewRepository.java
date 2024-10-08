package com.iiiiii.accountbook.review.command.domain.repository;

import com.iiiiii.accountbook.review.command.domain.aggregate.entity.StoreReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreReviewRepository extends JpaRepository<StoreReview, Integer> {

    @Override
    Optional<StoreReview>findById(Integer reviewCode);

}
