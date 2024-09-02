package com.iiiiii.accountbook.review.query.repository;

import com.iiiiii.accountbook.review.query.dto.selectStoreReviewByMemberOrStoreCodeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface StoreReviewMapper {


    List<selectStoreReviewByMemberOrStoreCodeDTO> selectReviewByStoreCode(int storeCode);

    List<selectStoreReviewByMemberOrStoreCodeDTO> selectReviewByMemberCode(int memberCode);
}
