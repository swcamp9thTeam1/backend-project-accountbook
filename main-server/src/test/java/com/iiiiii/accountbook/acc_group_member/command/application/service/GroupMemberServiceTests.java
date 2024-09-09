package com.iiiiii.accountbook.acc_group_member.command.application.service;

import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMember;
import com.iiiiii.accountbook.common.GroupRole;
import com.iiiiii.accountbook.exception.NotAllowedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;

@SpringBootTest
public class GroupMemberServiceTests {
    private GroupMemberService groupMemberService;
    private com.iiiiii.accountbook.acc_group_member.query.service.GroupMemberService queryService;

    @Autowired
    public void setCommandGroupMemberService(GroupMemberService groupMemberService,
                                             com.iiiiii.accountbook.acc_group_member.query.service.GroupMemberService queryService) {
        this.groupMemberService = groupMemberService;
        this.queryService = queryService;
    }

    @DisplayName("그룹 멤버 생성 확인 테스트")
    @Test
    public void testRegistAccGroup() {
        Assertions.assertNotNull(groupMemberService.registGroupMember(new GroupMember(10, 1, GroupRole.ROLE_UNALLOWED)));
    }

    @DisplayName("그룹 멤버 (역할) 수정 확인 테스트")
    @Test
    public void testModifyAccGroup() {
        GroupRole result = groupMemberService.modifyGroupMember(new GroupMember(10, 1, GroupRole.ROLE_FOLLOWER)).getRole();
        Assertions.assertEquals(GroupRole.ROLE_FOLLOWER, result);
    }

    @DisplayName("그룹 멤버 삭제 확인 테스트")
    @Test
    public void testDeleteAccGroup() throws NotAllowedException {
        groupMemberService.deleteGroupMember(new GroupMember(10, 1, GroupRole.ROLE_FOLLOWER));
        Assertions.assertTrue(queryService.findGroupMemberByRole(1, GroupRole.ROLE_FOLLOWER)
                .stream()
                .filter(member -> member.getMemberCode() == 10)
                .collect(Collectors.toList()).isEmpty()
        );
    }
}
