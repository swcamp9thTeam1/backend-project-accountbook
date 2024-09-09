package com.iiiiii.accountbook.acc_category.command.application.service;

import com.iiiiii.accountbook.acc_category.command.domain.aggregate.AccCategory;
import com.iiiiii.accountbook.common.InOrOut;
import com.iiiiii.accountbook.common.YesOrNo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccCategoryServiceTests {

    private AccCategoryService accCategoryService;
    private com.iiiiii.accountbook.acc_category.query.service.AccCategoryService queryService;

    @Autowired
    public AccCategoryServiceTests(AccCategoryService accCategoryService,
                                   com.iiiiii.accountbook.acc_category.query.service.AccCategoryService queryService) {
        this.accCategoryService = accCategoryService;
        this.queryService = queryService;
    }

    @DisplayName("가계부 카테고리 생성 확인 테스트")
    @Test
    public void testRegistAccCategory() {
        AccCategory newAccCategory = new AccCategory("카테고리생성테스트", InOrOut.I, YesOrNo.Y, YesOrNo.N, 3, 2);
        int result = accCategoryService.registAccCategory(newAccCategory).getCode();
        Assertions.assertNotNull(result > 0);
    }

    @DisplayName("가계부 카테고리 수정 확인 테스트")
    @Test
    public void testModifyAccCategory() {
        AccCategory modifyAccCategory = new AccCategory(16,"카테고리수정테스트", InOrOut.O, YesOrNo.N, YesOrNo.N, 2, 3);
        String result = accCategoryService.modifyAccCategory(modifyAccCategory).getName();
        Assertions.assertTrue("카테고리수정테스트".equals(result));
    }

    @DisplayName("가계부 카테고리 삭제 확인 테스트")
    @Test
    public void testDeleteAccCategory() {
        AccCategory deleteAccCategory = new AccCategory(16,"카테고리삭제테스트", InOrOut.O, YesOrNo.N, YesOrNo.N, 2, 3);
        accCategoryService.deleteAccCategory(deleteAccCategory);
        String result = queryService.findOneAccCategory(deleteAccCategory.getCode()).getIsDeleted().name();
        Assertions.assertEquals(result, YesOrNo.Y.name());
    }
}
