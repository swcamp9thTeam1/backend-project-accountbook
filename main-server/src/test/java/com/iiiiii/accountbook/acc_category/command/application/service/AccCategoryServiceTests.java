package com.iiiiii.accountbook.acc_category.command.application.service;

import com.iiiiii.accountbook.acc_category.command.domain.aggregate.AccCategory;
import com.iiiiii.accountbook.common.InOrOut;
import com.iiiiii.accountbook.common.YesOrNo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccCategoryServiceTests {

    @Autowired
    private AccCategoryService accCategoryService;

    @DisplayName("가계부 카테고리 생성 확인 테스트")
    @Test
    public void testRegistAccCategory() {

        AccCategory newAccCategory = new AccCategory("테스트", InOrOut.I, YesOrNo.Y, YesOrNo.N, 3, 2);

        accCategoryService.registAccCategory(newAccCategory);

    }

    @DisplayName("가계부 카테고리 수정 확인 테스트")
    @Test
    public void testModifyAccCategory() {

        AccCategory modifyAccCategory = new AccCategory(20,"휴양", InOrOut.O, YesOrNo.N, YesOrNo.N, 2, 3);

        accCategoryService.modifyAccCategory(modifyAccCategory);
    }

    @DisplayName("가계부 카테고리 삭제 확인 테스트")
    @Test
    public void testDeleteAccCategory() {
        AccCategory deleteAccCategory = new AccCategory(2,"카테고리2", InOrOut.O, YesOrNo.Y, YesOrNo.N, 2);

       accCategoryService.deleteAccCategory(deleteAccCategory);
    }
}
