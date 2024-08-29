package com.iiiiii.accountbook.acc_category.command.application.service;

import com.iiiiii.accountbook.acc_category.command.domain.aggregate.AccCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommandAccCategoryServiceTests {

    @Autowired
    private CommandAccCategoryService commandAccCategoryService;

    @DisplayName("가계부 카테고리 생성 확인 테스트")
    @Test
    public void testRegistAccCategory() {

        AccCategory newAccCategory = new AccCategory("음료", 'I', 'Y', 'N', 3, 2);

        commandAccCategoryService.registAccCategory(newAccCategory);

    }

    @DisplayName("가계부 카테고리 수정 확인 테스트")
    @Test
    public void testModifyAccCategory() {

        AccCategory modifyAccCategory = new AccCategory(1,"커피", 'I', 'N', 'N', 2, 1);

        commandAccCategoryService.modifyAccCategory(modifyAccCategory);
    }

    @DisplayName("가계부 카테고리 삭제 확인 테스트")
    @Test
    public void testDeleteAccCategory() {
        AccCategory deleteAccCategory = new AccCategory(1,"커피", 'I', 'N', 'N', 2, 1);

       commandAccCategoryService.deleteAccCategory(deleteAccCategory);
    }
}
