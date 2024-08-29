package com.iiiiii.accountbook.acc_category.query.service;

import com.iiiiii.accountbook.acc_category.query.dto.QueryAccCategoryDTO;
import com.iiiiii.accountbook.acc_category.query.repository.QueryAccCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("queryAccCategoryService")
public class QueryAccCategoryService {
    private QueryAccCategoryMapper queryAccCategoryMapper;

    @Autowired
    public QueryAccCategoryService(QueryAccCategoryMapper queryAccCategoryMapper) {
        this.queryAccCategoryMapper = queryAccCategoryMapper;
    }

    public List<QueryAccCategoryDTO> findAllAccCategory() {
        return queryAccCategoryMapper.selectAllAccCategory();
    }

    public List<QueryAccCategoryDTO> findAccCategoryByMemberCode(int memberCode) {
        return queryAccCategoryMapper.selectAccCategoryByMemberCode(memberCode);
    }

    public List<Integer> findAllAccCategoryCode(int memberCode) {
        return queryAccCategoryMapper.selectAllAccCategoryCode(memberCode);
    }

    public List<String> findAllAccCategoryName(int memberCode) {
        return queryAccCategoryMapper.selectAllAccCategoryName(memberCode);
    }

    public QueryAccCategoryDTO findOneAccCategory(int accCategoryCode) {
        return queryAccCategoryMapper.selectOneAccCategory(accCategoryCode);
    }

    public List<QueryAccCategoryDTO> findAccCategoryByIO(Map<String, Object> params) {
        return queryAccCategoryMapper.selectAccCategoryByIO(params);
    }

    public List<QueryAccCategoryDTO> findAccCategoryByIsDeleted(Map<String, Object> params) {
        return queryAccCategoryMapper.selectAccCategoryByIsDeleted(params);
    }

    public List<QueryAccCategoryDTO> findAccCategoryByVisibility(Map<String, Object> params) {
        return queryAccCategoryMapper.selectAccCategoryByVisibility(params);
    }
}
