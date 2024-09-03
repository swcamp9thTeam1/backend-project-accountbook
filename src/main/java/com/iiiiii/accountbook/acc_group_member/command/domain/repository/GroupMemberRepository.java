package com.iiiiii.accountbook.acc_group_member.command.domain.repository;

import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMemberRepository extends JpaRepository<GroupMemberEntity, Integer> {
}
