package com.iiiiii.accountbook.review.query.repository;

import com.iiiiii.accountbook.review.query.dto.StoreReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StoreReviewMapper {

    void insertReview(StoreReviewDTO storeReviewDTO);

    void updateReview(StoreReviewDTO storeReviewDTO);

    void deleteReview(StoreReviewDTO storeReviewDTO);

    void selectReview(StoreReviewDTO storeReviewDTO);

    StoreReviewDTO selectReviewById(int code);
//
//    // 가장 최근 리뷰 코드 가져오기 (리뷰 코드 맥스)
//    @Select("SELECT MAX(CODE) FROM STORE_REVIEW WHERE MEMBER_CODE = #{memberCode}")
//    Integer getLatestReviewCode(int memberCode);
//
//    // 특정 회원이 작성한 리뷰 코드 가져오기
//    @Select("SELECT CODE FROM REVIEW_TABLE WHERE MEMBER_CODE = #{memberCode}")
//    List<Integer> selectAllReviewCodeFromOneMember(int memberCode);
//
//    // 특정 회원이 조회한 + 특정 리뷰 가져오기
//    // 회원이 자신이 작성한 리뷰 보이면 -> 하나 클릭해서 수정, 삭제하는 로직을 위한 설계
//    @Select("SELECT * FROM STORE_REVIEW WHERE MEMBER_CODE = #{memberCode} AND CODE = #{reviewCode}")
//    StoreReviewDTO selectReviewByMemberAndCode(@Param("memberCode") int memberCode, @Param("reviewCode") int reviewCode);
}



