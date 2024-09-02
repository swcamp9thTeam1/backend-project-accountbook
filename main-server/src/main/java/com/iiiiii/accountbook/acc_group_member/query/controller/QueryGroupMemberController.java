package com.iiiiii.accountbook.acc_group_member.query.controller;

import com.iiiiii.accountbook.acc_group_member.query.dto.GroupMemberDTO;
import com.iiiiii.accountbook.acc_group_member.query.service.QueryGroupMemberService;
import com.iiiiii.accountbook.common.GroupRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acc-group-member")
public class QueryGroupMemberController {
    private QueryGroupMemberService queryGroupMemberService;

    @Autowired
    public QueryGroupMemberController(QueryGroupMemberService queryGroupMemberService) {
        this.queryGroupMemberService = queryGroupMemberService;
    }

    @GetMapping("/")
    public ResponseEntity<List<GroupMemberDTO>> findAllGroupMember() {

        List<GroupMemberDTO> groupMember = queryGroupMemberService.findAllGroupMember();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupMember);
    }

    @GetMapping("/groups/{memberCode}")
    public ResponseEntity<List<GroupMemberDTO>> findGroupByMemberCode(@PathVariable("memberCode") int memberCode) {

        List<GroupMemberDTO> groupMember = queryGroupMemberService.findGroupByMemberCode(memberCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupMember);
    }

    @GetMapping("/members/{groupCode}")
    public ResponseEntity<List<GroupMemberDTO>> findMemberByGroupCode(@PathVariable("groupCode") int groupCode) {

        List<GroupMemberDTO> groupMember = queryGroupMemberService.findMemberByGroupCode(groupCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupMember);
    }

    @GetMapping("/role")
    public ResponseEntity<List<GroupMemberDTO>> findGroupMemberByRole(@RequestParam("groupCode") int groupCode,
                                                                      @RequestParam("role") GroupRole role) {

        List<GroupMemberDTO> groupMember = queryGroupMemberService.findGroupMemberByRole(groupCode, role);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupMember);
    }
}
