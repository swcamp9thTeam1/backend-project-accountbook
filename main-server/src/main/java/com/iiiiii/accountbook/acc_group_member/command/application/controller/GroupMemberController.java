package com.iiiiii.accountbook.acc_group_member.command.application.controller;

import com.iiiiii.accountbook.acc_group_member.command.application.service.GroupMemberService;
import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/delete")
    public ResponseEntity<?> deleteGroupMember(@RequestBody GroupMember deleteGroupMember) {

        groupMemberService.deleteGroupMember(deleteGroupMember);

        return ResponseEntity
                .noContent()
                .build();
    }
}
