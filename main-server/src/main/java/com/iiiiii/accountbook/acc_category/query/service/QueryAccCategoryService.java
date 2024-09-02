package com.iiiiii.accountbook.acc_category.query.service;

import com.iiiiii.accountbook.acc_category.query.dto.QueryAccCategoryDTO;
import com.iiiiii.accountbook.acc_category.query.repository.QueryAccCategoryMapper;
import com.iiiiii.accountbook.common.InOrOut;
import com.iiiiii.accountbook.common.YesOrNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    public List<QueryAccCategoryDTO> findAccCategoryByIO(int memberCode, InOrOut financeType) {
        Map<String, Object> params = new HashMap<>();
        params.put("financeType", financeType.name());
        params.put("memberCode", memberCode);

        return queryAccCategoryMapper.selectAccCategoryByIO(params);
    }

    public List<QueryAccCategoryDTO> findAccCategoryByIsDeleted(int memberCode, YesOrNo isDeleted) {
        Map<String, Object> params = new HashMap<>();
        params.put("memberCode", memberCode);
        params.put("isDeleted", isDeleted.name());

        return queryAccCategoryMapper.selectAccCategoryByIsDeleted(params);
    }

    public List<QueryAccCategoryDTO> findAccCategoryByVisibility(int memberCode, YesOrNo visibility) {
        Map<String, Object> params = new HashMap<>();
        params.put("memberCode", memberCode);
        params.put("visibility", visibility.name());

        return queryAccCategoryMapper.selectAccCategoryByVisibility(params);
    }
}
