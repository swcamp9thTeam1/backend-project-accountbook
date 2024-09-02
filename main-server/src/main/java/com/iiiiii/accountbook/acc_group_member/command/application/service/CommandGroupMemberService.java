package com.iiiiii.accountbook.acc_group_member.command.application.service;

import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMember;
import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMemberEntity;
import com.iiiiii.accountbook.acc_group_member.command.domain.repository.CommandGroupMemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandGroupMemberService {
    private CommandGroupMemberRepository commandGroupMemberRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CommandGroupMemberService(CommandGroupMemberRepository commandGroupMemberRepository,
                                     ModelMapper modelMapper) {
        this.commandGroupMemberRepository = commandGroupMemberRepository;
        this.modelMapper = modelMapper;
    }

    public void registGroupMember(GroupMember newGroupMember) {
        // if newGroupMember.getMemberCode in member.code 테이블
        GroupMemberEntity registMember = modelMapper.map(newGroupMember, GroupMemberEntity.class);
        commandGroupMemberRepository.save(registMember);
    }

    public void modifyGroupMember(GroupMember modifyGroupMember) {
        GroupMemberEntity modifyMember = modelMapper.map(modifyGroupMember, GroupMemberEntity.class);
        commandGroupMemberRepository.saveAndFlush(modifyMember);
    }


    public void deleteGroupMember(GroupMember deleteGroupMember) {
        GroupMemberEntity deleteMember = modelMapper.map(deleteGroupMember, GroupMemberEntity.class);
        commandGroupMemberRepository.delete(deleteMember);
    }
}
