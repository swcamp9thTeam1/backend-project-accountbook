package com.iiiiii.accountbook.acc_category.query.service;

import com.iiiiii.accountbook.acc_category.query.dto.AccCategoryDTO;
import com.iiiiii.accountbook.common.InOrOut;
import com.iiiiii.accountbook.common.YesOrNo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class AccCategoryServiceTests {

    @Autowired
    private AccCategoryService accCategoryService;

    @DisplayName("가계부 카테고리 목록 조회 확인 테스트")
    @Test
    public void testFindAllAccCategory() {
        List<AccCategoryDTO> list = accCategoryService.findAllAccCategory();

        Assertions.assertTrue(!list.isEmpty());
    }

    @DisplayName("회원의 가계부 카테고리 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void testFindAccCategoryByMemberCode(int memberCode) {
        List<AccCategoryDTO> list = accCategoryService.findAccCategoryByMemberCode(memberCode);

        Assertions.assertTrue(!list.isEmpty());
    }

    @DisplayName("회원의 가계부 카테고리명 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void testFindAccCategoryName(int memberCode) {
        List<String> list = accCategoryService.findAllAccCategoryName(memberCode);

        Assertions.assertTrue(!list.isEmpty());
    }

    @DisplayName("회원의 가계부 카테고리 코드 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void testFindAccCategoryCode(int memberCode) {
        List<Integer> list = accCategoryService.findAllAccCategoryCode(memberCode);

        Assertions.assertTrue(list.size()>0);
    }

    @DisplayName("회원의 수입/지출 별 가계부 카테고리 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void testFindAccCategoryByIO(int memberCode) {
        List<AccCategoryDTO> list = accCategoryService.findAccCategoryByIO(memberCode, InOrOut.O);

        Assertions.assertTrue(!list.isEmpty());
    }

    @DisplayName("회원의 삭제 여부 별 가계부 카테고리 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void testFindAccCategoryByIsDeleted(int memberCode) {
        List<AccCategoryDTO> list = accCategoryService.findAccCategoryByIsDeleted(memberCode, YesOrNo.N);

        Assertions.assertTrue(!list.isEmpty());
    }

    @DisplayName("회원의 공개 여부 별 가계부 카테고리 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void testFindAccCategoryByVisibility(int memberCode) {
        List<AccCategoryDTO> list = accCategoryService.findAccCategoryByIsDeleted(memberCode, YesOrNo.Y);

        Assertions.assertTrue(!list.isEmpty());
    }
}
