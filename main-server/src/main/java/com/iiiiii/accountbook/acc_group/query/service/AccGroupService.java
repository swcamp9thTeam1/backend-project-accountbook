package com.iiiiii.accountbook.acc_group.query.service;

import com.iiiiii.accountbook.acc_group.query.dto.AccGroupDTO;
import com.iiiiii.accountbook.acc_group.query.repository.AccGroupMapper;
import com.iiiiii.accountbook.acc_group_member.query.dto.GroupMemberDTO;
import com.iiiiii.accountbook.acc_group_member.query.service.GroupMemberService;
import com.iiiiii.accountbook.common.GroupRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("QueryAccGroupService")
public class AccGroupService {
    private AccGroupMapper accGroupMapper;
    private GroupMemberService groupMemberService;

    @Autowired
    public AccGroupService(AccGroupMapper accGroupMapper, GroupMemberService groupMemberService) {
        this.accGroupMapper = accGroupMapper;
        this.groupMemberService = groupMemberService;
    }

    public List<AccGroupDTO> findAllAccGroup() {
        return accGroupMapper.selectAllAccGroups();
    }

    public AccGroupDTO findOneAccGroup(int code) {
        return accGroupMapper.selectOneAccGroup(code);
    }

    public List<Integer> findAccGroupCodes() {
        return accGroupMapper.selectAllAccGroupCodes();
    }

    public List<String> findAccGroupNames() {
        return accGroupMapper.selectAllAccGroupNames();
    }

    public List<GroupMemberDTO> findAccGroupMembers(int groupCode) {
        return groupMemberService.findMemberByGroupCode(groupCode);
    }

    public List<GroupMemberDTO> findAccGroupMemberByRole(int groupCode, GroupRole role) {
        return groupMemberService.findGroupMemberByRole(groupCode, role);
    }
}
