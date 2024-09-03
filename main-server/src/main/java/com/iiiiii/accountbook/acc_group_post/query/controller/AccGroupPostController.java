package com.iiiiii.accountbook.acc_group_post.query.controller;

import com.iiiiii.accountbook.acc_group_post.query.dto.AccGroupPostDTO;
import com.iiiiii.accountbook.acc_group_post.query.service.AccGroupPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("QueryAccGroupPostController")
@RequestMapping("/acc-group-post")
public class AccGroupPostController {
    private AccGroupPostService accGroupPostService;

    @Autowired
    public AccGroupPostController(AccGroupPostService accGroupPostService) {
        this.accGroupPostService = accGroupPostService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AccGroupPostDTO>> findAllGroupPosts() {

        List<AccGroupPostDTO> groupPosts = accGroupPostService.findAllGroupPost();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupPosts);
    }

    @GetMapping("/{groupPostCode}")
    public ResponseEntity<AccGroupPostDTO> findGroupPostByGroupPostCode(@PathVariable("groupPostCode") int groupPostCode) {

        AccGroupPostDTO groupPosts = accGroupPostService.findGroupPostByGroupPostCode(groupPostCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupPosts);
    }

    @GetMapping("/groups/{groupCode}")
    public ResponseEntity<List<AccGroupPostDTO>> findGroupPostByGroupCode(@PathVariable("groupCode") int groupCode) {

        List<AccGroupPostDTO> groupPosts = accGroupPostService.findGroupPostByGroupCode(groupCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupPosts);
    }

    @GetMapping("/members/{memberCode}")
    public ResponseEntity<List<AccGroupPostDTO>> findGroupPostByMemberCode(@PathVariable("memberCode") int memberCode) {

        List<AccGroupPostDTO> groupPosts = accGroupPostService.findGroupPostByMemberCode(memberCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupPosts);
    }




}
