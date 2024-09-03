package com.iiiiii.accountbook.acc_group_member.command.application.service;

import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMember;
import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMemberEntity;
import com.iiiiii.accountbook.acc_group_member.command.domain.repository.GroupMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CommandGroupMemberService")
public class GroupMemberService {
    private GroupMemberRepository groupMemberRepository;
    private ModelMapper modelMapper;

    @Autowired
    public GroupMemberService(GroupMemberRepository groupMemberRepository,
                              ModelMapper modelMapper) {
        this.groupMemberRepository = groupMemberRepository;
        this.modelMapper = modelMapper;
    }

    public void registGroupMember(GroupMember newGroupMember) {
        // if newGroupMember.getMemberCode in member.code 테이블
        GroupMemberEntity registMember = modelMapper.map(newGroupMember, GroupMemberEntity.class);
        groupMemberRepository.save(registMember);
    }

    public void modifyGroupMember(GroupMember modifyGroupMember) {
        GroupMemberEntity modifyMember = modelMapper.map(modifyGroupMember, GroupMemberEntity.class);
        groupMemberRepository.saveAndFlush(modifyMember);
    }


    public void deleteGroupMember(GroupMember deleteGroupMember) {
        GroupMemberEntity deleteMember = modelMapper.map(deleteGroupMember, GroupMemberEntity.class);
        groupMemberRepository.delete(deleteMember);
    }
}
