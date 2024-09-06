package com.iiiiii.accountbook.acc_group_member.command.application.controller;

import com.iiiiii.accountbook.acc_group_member.command.application.service.GroupMemberService;
import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMember;
import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMemberEntity;
import com.iiiiii.accountbook.exception.NotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("CommandGroupMemberController")
@RequestMapping("/acc-group-member")
public class GroupMemberController {
    private GroupMemberService groupMemberService;

    @Autowired
    public GroupMemberController(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }

    @PostMapping("/regist")
    public ResponseEntity<GroupMemberEntity> registGroupMember(@RequestBody GroupMember newGroupMember) {
        GroupMemberEntity result = groupMemberService.registGroupMember(newGroupMember);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PostMapping("/modify")
    public ResponseEntity<GroupMemberEntity> modifyGroupMember(@RequestBody GroupMember modifyGroupMember) {
        GroupMemberEntity result = groupMemberService.modifyGroupMember(modifyGroupMember);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteGroupMember(@RequestBody GroupMember deleteGroupMember) throws NotAllowedException {
        groupMemberService.deleteGroupMember(deleteGroupMember);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
