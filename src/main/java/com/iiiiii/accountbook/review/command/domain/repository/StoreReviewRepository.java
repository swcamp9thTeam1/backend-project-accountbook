package com.iiiiii.accountbook.review.command.domain.repository;

import com.iiiiii.accountbook.review.command.domain.aggregate.entity.StoreReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreReviewRepository extends JpaRepository<StoreReview, Integer> {


}
