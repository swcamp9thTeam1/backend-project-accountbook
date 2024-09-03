package com.iiiiii.accountbook.acc_category.query.service;

import com.iiiiii.accountbook.acc_category.query.dto.AccCategoryDTO;
import com.iiiiii.accountbook.acc_category.query.repository.AccCategoryMapper;
import com.iiiiii.accountbook.common.InOrOut;
import com.iiiiii.accountbook.common.YesOrNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("QueryAccCategoryService")
public class AccCategoryService {
    private AccCategoryMapper accCategoryMapper;

    @Autowired
    public AccCategoryService(AccCategoryMapper accCategoryMapper) {
        this.accCategoryMapper = accCategoryMapper;
    }

    public List<AccCategoryDTO> findAllAccCategory() {
        return accCategoryMapper.selectAllAccCategory();
    }

    public List<AccCategoryDTO> findAccCategoryByMemberCode(int memberCode) {
        return accCategoryMapper.selectAccCategoryByMemberCode(memberCode);
    }

    public List<Integer> findAllAccCategoryCode(int memberCode) {
        return accCategoryMapper.selectAllAccCategoryCode(memberCode);
    }

    public List<String> findAllAccCategoryName(int memberCode) {
        return accCategoryMapper.selectAllAccCategoryName(memberCode);
    }

    public AccCategoryDTO findOneAccCategory(int accCategoryCode) {
        return accCategoryMapper.selectOneAccCategory(accCategoryCode);
    }

    public List<AccCategoryDTO> findAccCategoryByIO(int memberCode, InOrOut financeType) {
        Map<String, Object> params = new HashMap<>();
        params.put("financeType", financeType.name());
        params.put("memberCode", memberCode);

        return accCategoryMapper.selectAccCategoryByIO(params);
    }

    public List<AccCategoryDTO> findAccCategoryByIsDeleted(int memberCode, YesOrNo isDeleted) {
        Map<String, Object> params = new HashMap<>();
        params.put("memberCode", memberCode);
        params.put("isDeleted", isDeleted.name());

        return accCategoryMapper.selectAccCategoryByIsDeleted(params);
    }

    public List<AccCategoryDTO> findAccCategoryByVisibility(int memberCode, YesOrNo visibility) {
        Map<String, Object> params = new HashMap<>();
        params.put("memberCode", memberCode);
        params.put("visibility", visibility.name());

        return accCategoryMapper.selectAccCategoryByVisibility(params);
    }
}
