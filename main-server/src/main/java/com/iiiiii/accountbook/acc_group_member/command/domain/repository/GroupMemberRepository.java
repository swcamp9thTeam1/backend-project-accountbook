package com.iiiiii.accountbook.acc_group_member.command.domain.repository;

import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMemberEntity, Integer> {
    List<GroupMemberEntity> findByGroupCode(Integer groupCode);
}
