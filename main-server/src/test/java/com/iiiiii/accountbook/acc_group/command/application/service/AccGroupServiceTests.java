package com.iiiiii.accountbook.acc_group.command.application.service;

import com.iiiiii.accountbook.acc_group.command.domain.aggregate.AccGroup;
import com.iiiiii.accountbook.exception.NotAllowedException;
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
        accGroupService.registAccGroup(1, new AccGroup("테스트 그룹", "그룹 생성 테스트!!!"));
    }

    @DisplayName("그룹 수정 확인 테스트")
    @Test
    public void testModifyAccGroup() throws NotAllowedException {
        accGroupService.modifyAccGroup(1, new AccGroup(1, "그룹99", "소개99"));
    }

    @DisplayName("그룹 삭제 확인 테스트")
    @Test
    public void testDeleteAccGroup() throws NotAllowedException {
        accGroupService.deleteAccGroup(1, new AccGroup(2, "그룹2", "소개2"));
    }
}
