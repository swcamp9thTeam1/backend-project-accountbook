package com.iiiiii.accountbook.acc_group_member.command.application.controller;

import com.iiiiii.accountbook.acc_group_member.command.application.service.GroupMemberService;
import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMember;
import com.iiiiii.accountbook.exception.NotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> registGroupMember(@RequestBody GroupMember newGroupMember) {
        groupMemberService.registGroupMember(newGroupMember);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyGroupMember(@RequestBody GroupMember modifyGroupMember) {

        groupMemberService.modifyGroupMember(modifyGroupMember);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteGroupMember(@RequestBody GroupMember deleteGroupMember) throws NotAllowedException {

        groupMemberService.deleteGroupMember(deleteGroupMember);

        return ResponseEntity
                .noContent()
                .build();
    }
}
