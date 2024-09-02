package com.iiiiii.accountbook.acc_group_member.command.application.controller;

import com.iiiiii.accountbook.acc_group_member.command.application.service.CommandGroupMemberService;
import com.iiiiii.accountbook.acc_group_member.command.domain.aggregate.GroupMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acc-group-member")
public class CommandGroupMemberController {
    private CommandGroupMemberService commandGroupMemberService;

    @Autowired
    public CommandGroupMemberController(CommandGroupMemberService commandGroupMemberService) {
        this.commandGroupMemberService = commandGroupMemberService;
    }

    @PostMapping("/regist")
    public ResponseEntity<?> registGroupMember(@RequestBody GroupMember newGroupMember) {

        commandGroupMemberService.registGroupMember(newGroupMember);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyGroupMember(@RequestBody GroupMember modifyGroupMember) {

        commandGroupMemberService.modifyGroupMember(modifyGroupMember);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteGroupMember(@RequestBody GroupMember deleteGroupMember) {

        commandGroupMemberService.deleteGroupMember(deleteGroupMember);

        return ResponseEntity
                .noContent()
                .build();
    }
}
