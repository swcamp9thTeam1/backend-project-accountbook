package com.iiiiii.accountbook.acc_group_member.command.application.service;

import com.iiiiii.accountbook.acc_group.command.domain.aggregate.CommandAccGroup;
import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMember;
import com.iiiiii.accountbook.common.GroupRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommandGroupMemberTests {
    private CommandGroupMemberService commandGroupMemberService;

    @Autowired
    public void setCommandGroupMemberService(CommandGroupMemberService commandGroupMemberService) {
        this.commandGroupMemberService = commandGroupMemberService;
    }

    @DisplayName("그룹 멤버 생성 확인 테스트")
    @Test
    public void testRegistAccGroup() {
        commandGroupMemberService.registGroupMember(new GroupMember(10, 1, GroupRole.ROLE_UNALLOWED));
    }

    @DisplayName("그룹 멤버 (역할) 수정 확인 테스트")
    @Test
    public void testModifyAccGroup() {
        commandGroupMemberService.modifyGroupMember(new GroupMember(10, 1, GroupRole.ROLE_FOLLOWER));
    }

    @DisplayName("그룹 멤버 삭제 확인 테스트")
    @Test
    public void testDeleteAccGroup() {
        commandGroupMemberService.deleteGroupMember(new GroupMember(10, 1, GroupRole.ROLE_FOLLOWER));
    }
}
