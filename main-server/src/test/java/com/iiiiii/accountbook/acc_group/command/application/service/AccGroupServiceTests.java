package com.iiiiii.accountbook.acc_group.command.application.service;

import com.iiiiii.accountbook.acc_group.command.domain.aggregate.AccGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccGroupServiceTests {
    private AccGroupService accGroupService;

    @Autowired
    public void setCommandAccGroupService(AccGroupService accGroupService) {
        this.accGroupService = accGroupService;
    }

    @DisplayName("그룹 생성 확인 테스트")
    @Test
    public void testRegistAccGroup() {
        accGroupService.registAccGroup(new AccGroup("테스트 그룹", "그룹 생성 테스트!!!"));
    }

    @DisplayName("그룹 수정 확인 테스트")
    @Test
    public void testModifyAccGroup() {
        accGroupService.modifyAccGroup(new AccGroup(1, "그룹99", "소개99"));
    }

    @DisplayName("그룹 삭제 확인 테스트")
    @Test
    public void testDeleteAccGroup() {
        accGroupService.deleteAccGroup(new AccGroup(2, "그룹2", "소개2"));
    }
}
