package com.iiiiii.accountbook.acc_group_member.query.service;

import com.iiiiii.accountbook.acc_group_member.query.dto.GroupMemberDTO;
import com.iiiiii.accountbook.acc_group_member.query.repository.QueryGroupMemberMapper;
import com.iiiiii.accountbook.common.GroupRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryGroupMemberService {
    private QueryGroupMemberMapper queryGroupMemberMapper;

    @Autowired
    public QueryGroupMemberService(QueryGroupMemberMapper queryGroupMemberMapper) {
        this.queryGroupMemberMapper = queryGroupMemberMapper;
    }


    public List<GroupMemberDTO> findAllGroupMember() {
        return queryGroupMemberMapper.selectAllGroupMember();
    }

    public List<GroupMemberDTO> findGroupByMemberCode(int memberCode) {
        return queryGroupMemberMapper.selectGroupByMemberCode(memberCode);
    }

    public List<GroupMemberDTO> findMemberByGroupCode(int groupCode) {
        return queryGroupMemberMapper.selectGroupMemberByGroupCode(groupCode);
    }

    public List<GroupMemberDTO> findGroupMemberByRole(int groupCode, GroupRole role) {
        Map<String, Object> params = new HashMap<>();
        params.put("groupCode", groupCode);
        params.put("role", role.name());

        return queryGroupMemberMapper.selectGroupMemberByRole(params);
    }
}
