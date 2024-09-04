package com.iiiiii.accountbook.acc_group_member.query.service;

import com.iiiiii.accountbook.acc_group_member.query.dto.GroupMemberDTO;
import com.iiiiii.accountbook.acc_group_member.query.repository.GroupMemberMapper;
import com.iiiiii.accountbook.common.GroupRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("QueryGroupMemberService")
public class GroupMemberService {
    private GroupMemberMapper groupMemberMapper;

    @Autowired
    public GroupMemberService(GroupMemberMapper groupMemberMapper) {
        this.groupMemberMapper = groupMemberMapper;
    }

    public List<GroupMemberDTO> findAllGroupMember() {
        return groupMemberMapper.selectAllGroupMember();
    }

    public List<GroupMemberDTO> findGroupByMemberCode(int memberCode) {
        return groupMemberMapper.selectGroupByMemberCode(memberCode);
    }

    public List<GroupMemberDTO> findMemberByGroupCode(int groupCode) {
        return groupMemberMapper.selectGroupMemberByGroupCode(groupCode);
    }

    public List<GroupMemberDTO> findGroupMemberByRole(int groupCode, GroupRole role) {
        Map<String, Object> params = new HashMap<>();
        params.put("groupCode", groupCode);
        params.put("role", role.name());

        return groupMemberMapper.selectGroupMemberByRole(params);
    }
}
