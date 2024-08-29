package com.iiiiii.accountbook.acc_category.query.service;

import com.iiiiii.accountbook.acc_category.query.dto.QueryAccCategoryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
public class QueryAccCategoryServiceTests {

    @Autowired
    private QueryAccCategoryService queryAccCategoryService;

    @DisplayName("가계부 카테고리 목록 조회 확인 테스트")
    @Test
    public void testFindAllAccCategory() {
        List<QueryAccCategoryDTO> list = queryAccCategoryService.findAllAccCategory();

        Assertions.assertTrue(!list.isEmpty());
    }

    @DisplayName("회원의 가계부 카테고리 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void testFindAccCategoryByMemberCode(int memberCode) {
        List<QueryAccCategoryDTO> list = queryAccCategoryService.findAccCategoryByMemberCode(memberCode);

        Assertions.assertTrue(!list.isEmpty());
    }

    @DisplayName("회원의 가계부 카테고리명 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void testFindAccCategoryName(int memberCode) {
        List<String> list = queryAccCategoryService.findAllAccCategoryName(memberCode);

        Assertions.assertTrue(!list.isEmpty());
    }

    @DisplayName("회원의 가계부 카테고리 코드 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void testFindAccCategoryCode(int memberCode) {
        List<Integer> list = queryAccCategoryService.findAllAccCategoryCode(memberCode);

        Assertions.assertTrue(list.size()>0);
    }

    @DisplayName("회원의 수입/지출 별 가계부 카테고리 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(chars = {'I', 'O'})
    public void testFindAccCategoryByIO(char financeType) {
        int memberCode = 1;
        Map<String, Object> params = new HashMap<>();
        params.put("financeType", financeType);
        params.put("memberCode", memberCode);
        List<QueryAccCategoryDTO> list = queryAccCategoryService.findAccCategoryByIO(params);

        Assertions.assertTrue(!list.isEmpty());
    }

    @DisplayName("회원의 삭제 여부 별 가계부 카테고리 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(chars = {'Y', 'N'})
    public void testFindAccCategoryByIsDeleted(char isDeleted) {
        int memberCode = 1;
        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", isDeleted);
        params.put("memberCode", memberCode);
        List<QueryAccCategoryDTO> list = queryAccCategoryService.findAccCategoryByIsDeleted(params);

        Assertions.assertTrue(!list.isEmpty());
    }

    @DisplayName("회원의 공개 여부 별 가계부 카테고리 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(chars = {'Y', 'N'})
    public void testFindAccCategoryByVisibility(char visibility) {
        int memberCode = 1;
        Map<String, Object> params = new HashMap<>();
        params.put("visibility", visibility);
        params.put("memberCode", memberCode);
        List<QueryAccCategoryDTO> list = queryAccCategoryService.findAccCategoryByIsDeleted(params);

        Assertions.assertTrue(!list.isEmpty());
    }
}
