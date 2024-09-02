package com.iiiiii.accountbook.acc_group_member.query.repository;

import com.iiiiii.accountbook.acc_group_member.query.dto.GroupMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QueryGroupMemberMapper {
    List<GroupMemberDTO> selectAllGroupMember();

    List<GroupMemberDTO> selectGroupByMemberCode(int memberCode);

    List<GroupMemberDTO> selectGroupMemberByGroupCode(int groupCode);

    List<GroupMemberDTO> selectGroupMemberByRole(Map<String, Object> params);
}
