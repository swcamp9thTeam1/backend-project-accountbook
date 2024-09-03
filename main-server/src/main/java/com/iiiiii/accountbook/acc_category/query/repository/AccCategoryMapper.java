package com.iiiiii.accountbook.acc_category.query.repository;

import com.iiiiii.accountbook.acc_category.query.dto.AccCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccCategoryMapper {
    List<AccCategoryDTO> selectAllAccCategory();

    List<AccCategoryDTO> selectAccCategoryByMemberCode(int memberCode);

    List<Integer> selectAllAccCategoryCode(int memberCode);

    List<String> selectAllAccCategoryName(int memberCode);

    AccCategoryDTO selectOneAccCategory(int accCategoryCode);

    List<AccCategoryDTO> selectAccCategoryByIO(Map<String, Object> params);

    List<AccCategoryDTO> selectAccCategoryByIsDeleted(Map<String, Object> params);

    List<AccCategoryDTO> selectAccCategoryByVisibility(Map<String, Object> params);
}
