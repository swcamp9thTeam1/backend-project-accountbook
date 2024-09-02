package com.iiiiii.accountbook.acc_category.query.repository;

import com.iiiiii.accountbook.acc_category.query.dto.QueryAccCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QueryAccCategoryMapper {
    List<QueryAccCategoryDTO> selectAllAccCategory();

    List<QueryAccCategoryDTO> selectAccCategoryByMemberCode(int memberCode);

    List<Integer> selectAllAccCategoryCode(int memberCode);

    List<String> selectAllAccCategoryName(int memberCode);

    QueryAccCategoryDTO selectOneAccCategory(int accCategoryCode);

    List<QueryAccCategoryDTO> selectAccCategoryByIO(Map<String, Object> params);

    List<QueryAccCategoryDTO> selectAccCategoryByIsDeleted(Map<String, Object> params);

    List<QueryAccCategoryDTO> selectAccCategoryByVisibility(Map<String, Object> params);
}
