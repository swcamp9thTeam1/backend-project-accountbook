package com.iiiiii.accountbook.acc_group.command.application.service;

import com.iiiiii.accountbook.acc_group.command.domain.aggregate.AccGroup;
import com.iiiiii.accountbook.acc_group.command.domain.aggregate.AccGroupEntity;
import com.iiiiii.accountbook.acc_group.command.domain.repository.AccGroupRepository;
import com.iiiiii.accountbook.acc_group_member.command.application.service.GroupMemberService;
import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMember;
import com.iiiiii.accountbook.acc_group_member.query.dto.GroupMemberDTO;
import com.iiiiii.accountbook.common.GroupRole;
import com.iiiiii.accountbook.exception.NotAllowedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("CommandAccGroupService")
public class AccGroupService {
    private AccGroupRepository accGroupRepository;
    private ModelMapper modelMapper;
    private com.iiiiii.accountbook.acc_group.query.service.AccGroupService accGroupService;
    private com.iiiiii.accountbook.acc_group_member.query.service.GroupMemberService queryMemberService;
    private GroupMemberService commandMemberService;

    @Autowired
    public AccGroupService(AccGroupRepository accGroupRepository,
                           ModelMapper modelMapper,
                           com.iiiiii.accountbook.acc_group.query.service.AccGroupService accGroupService,
                           com.iiiiii.accountbook.acc_group_member.query.service.GroupMemberService queryMemberService,
                           @Lazy GroupMemberService commandMemberService) {
        this.accGroupRepository = accGroupRepository;
        this.modelMapper = modelMapper;
        this.accGroupService = accGroupService;
        this.queryMemberService = queryMemberService;
        this.commandMemberService = commandMemberService;
    }

    public void registAccGroup(int memberCode, AccGroup newAccGroup) {
        List<String> names = accGroupService.findAccGroupNames()
                .stream()
                .filter(name -> name.equals((newAccGroup.getName())))
                .collect(Collectors.toList());

        if (names.size() == 0) {
            AccGroupEntity regist = modelMapper.map(newAccGroup, AccGroupEntity.class);
            AccGroupEntity newGroup = accGroupRepository.save(regist);

            // 그룹 생성시 생성자가 그룹장이 됨
            commandMemberService.registGroupMember(new GroupMember(memberCode, newGroup.getCode(), GroupRole.ROLE_LEADER));
        }
    }

    public void modifyAccGroup(int memberCode, AccGroup modifyAccGroup) throws NotAllowedException {
        List<GroupMemberDTO> member = queryMemberService.findGroupMemberByRole(modifyAccGroup.getCode(), GroupRole.ROLE_LEADER);

        // 그룹장만 수정 가능
        if (memberCode == member.get(0).getMemberCode()) {
            AccGroupEntity modify = modelMapper.map(modifyAccGroup, AccGroupEntity.class);
            accGroupRepository.saveAndFlush(modify);
        } else {
            throw new NotAllowedException("수정");
        }
    }

    public void deleteAccGroup(int memberCode, AccGroup deleteAccGroup) throws NotAllowedException {
        List<GroupMemberDTO> member = queryMemberService.findGroupMemberByRole(deleteAccGroup.getCode(), GroupRole.ROLE_LEADER);

        // 그룹장만 삭제 가능
        if (memberCode == member.get(0).getMemberCode()) {
            AccGroupEntity delete = modelMapper.map(deleteAccGroup, AccGroupEntity.class);
            accGroupRepository.deleteById(delete.getCode());
        } else
            throw new NotAllowedException("삭제");
    }
}
