package com.iiiiii.accountbook.acc_group_member.query.service;

import com.iiiiii.accountbook.acc_group_member.query.dto.GroupMemberDTO;
import com.iiiiii.accountbook.common.GroupRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GroupMemberServiceTests {
    private GroupMemberService groupMemberService;

    @Autowired
    public void setQueryGroupMemberService(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }

    @DisplayName("그룹 멤버 목록 조회 확인 테스트")
    @Test
    public void testFindAllGroupMember() {
        List<GroupMemberDTO> list = groupMemberService.findAllGroupMember();

        assertTrue(!list.isEmpty());
    }

    @DisplayName("회원이 가입한 그룹 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void testFindGroupByMemberCode(int memberCode) {
        List<GroupMemberDTO> list = groupMemberService.findGroupByMemberCode(memberCode);

        assertTrue(!list.isEmpty());
    }

    @DisplayName("그룹에 가입된 멤버 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void testFindMemberByGroupCode(int groupCode) {
        List<GroupMemberDTO> list = groupMemberService.findMemberByGroupCode(groupCode);

        assertTrue(!list.isEmpty());
    }

    @DisplayName("그룹의 역할별 멤버 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void testFindAllGroupMember(int groupCode) {
        List<GroupMemberDTO> list = groupMemberService.findGroupMemberByRole(groupCode, GroupRole.ROLE_FOLLOWER);

        assertTrue(!list.isEmpty());
    }
}
