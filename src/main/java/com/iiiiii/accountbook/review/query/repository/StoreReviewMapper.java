package com.iiiiii.accountbook.review.query.repository;

import com.iiiiii.accountbook.review.query.dto.StoreReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreReviewMapper {

    void insertReview(StoreReviewDTO storeReviewDTO);

    void updateReview(StoreReviewDTO storeReviewDTO);

    void deleteReview(StoreReviewDTO storeReviewDTO);

    void selectReview(StoreReviewDTO storeReviewDTO);

    StoreReviewDTO selectReviewById(int code);


}
