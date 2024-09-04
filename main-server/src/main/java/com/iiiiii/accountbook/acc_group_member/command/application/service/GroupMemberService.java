package com.iiiiii.accountbook.acc_group_member.command.application.service;

import com.iiiiii.accountbook.acc_group.command.domain.aggregate.AccGroup;
import com.iiiiii.accountbook.acc_group.query.dto.AccGroupDTO;
import com.iiiiii.accountbook.acc_group.query.service.AccGroupService;
import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMember;
import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMemberEntity;
import com.iiiiii.accountbook.acc_group_member.command.domain.repository.GroupMemberRepository;
import com.iiiiii.accountbook.common.GroupRole;
import com.iiiiii.accountbook.exception.NotAllowedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service("CommandGroupMemberService")
public class GroupMemberService {
    private GroupMemberRepository groupMemberRepository;
    private ModelMapper modelMapper;
    private AccGroupService queryGroupService;
    private com.iiiiii.accountbook.acc_group.command.application.service.AccGroupService commandGroupService;

    @Autowired
    public GroupMemberService(GroupMemberRepository groupMemberRepository,
                              ModelMapper modelMapper,
                              AccGroupService queryGroupService,
                              @Lazy com.iiiiii.accountbook.acc_group.command.application.service.AccGroupService commandGroupService) {
        this.groupMemberRepository = groupMemberRepository;
        this.modelMapper = modelMapper;
        this.queryGroupService = queryGroupService;
        this.commandGroupService = commandGroupService;
    }

    public void registGroupMember(GroupMember newGroupMember) {
        GroupMemberEntity registMember = modelMapper.map(newGroupMember, GroupMemberEntity.class);
        groupMemberRepository.save(registMember);
    }

    public void modifyGroupMember(GroupMember modifyGroupMember) {
        GroupMemberEntity modifyMember = modelMapper.map(modifyGroupMember, GroupMemberEntity.class);
        groupMemberRepository.saveAndFlush(modifyMember);
    }


    public void deleteGroupMember(GroupMember deleteGroupMember) throws NotAllowedException {
        Random rand = new Random();

        // 삭제할 멤버가 그룹장일 경우
        if (GroupRole.ROLE_LEADER.equals(deleteGroupMember.getRole())) {
            // 그룹원 조회
            List<GroupMemberEntity> members = groupMemberRepository.findByGroupCode(deleteGroupMember.getGroupCode())
                    .stream()
                    .filter(member -> GroupRole.ROLE_FOLLOWER.equals(member.getRole()))
                    .collect(Collectors.toList());

            if (members.size() == 0) {
                // 그룹원이 없는 경우 그룹 삭제
                AccGroupDTO group = queryGroupService.findOneAccGroup(deleteGroupMember.getGroupCode());
                commandGroupService.deleteAccGroup(deleteGroupMember.getMemberCode(), modelMapper.map(group, AccGroup.class));
            } else {
                // 그룹원 중 랜덤으로 그룹장 선정
                int random = rand.nextInt((members.size()-1-0) + 1) + 0;
                GroupMemberEntity newLeader = members.get(random);

                newLeader.setRole(GroupRole.ROLE_LEADER);
                groupMemberRepository.saveAndFlush(newLeader);

                GroupMemberEntity deleteMember = modelMapper.map(deleteGroupMember, GroupMemberEntity.class);
                groupMemberRepository.delete(deleteMember);
            }
        }
    }
}
