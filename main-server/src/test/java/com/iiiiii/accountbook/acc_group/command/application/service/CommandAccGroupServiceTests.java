package com.iiiiii.accountbook.acc_group.command.application.service;

import com.iiiiii.accountbook.acc_group.command.domain.aggregate.CommandAccGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommandAccGroupServiceTests {
    private CommandAccGroupService commandAccGroupService;

    @Autowired
    public void setCommandAccGroupService(CommandAccGroupService commandAccGroupService) {
        this.commandAccGroupService = commandAccGroupService;
    }

    @DisplayName("그룹 생성 확인 테스트")
    @Test
    public void testRegistAccGroup() {
        commandAccGroupService.registAccGroup(new CommandAccGroup("테스트 그룹", "그룹 생성 테스트!!!"));
    }

    @DisplayName("그룹 수정 확인 테스트")
    @Test
    public void testModifyAccGroup() {
        commandAccGroupService.modifyAccGroup(new CommandAccGroup(1, "그룹99", "소개99"));
    }

    @DisplayName("그룹 삭제 확인 테스트")
    @Test
    public void testDeleteAccGroup() {
        commandAccGroupService.deleteAccGroup(new CommandAccGroup(2, "그룹2", "소개2"));
    }
}
