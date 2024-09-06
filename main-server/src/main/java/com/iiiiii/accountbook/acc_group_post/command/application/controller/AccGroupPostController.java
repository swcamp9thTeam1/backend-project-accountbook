package com.iiiiii.accountbook.acc_group_post.command.application.controller;

import com.iiiiii.accountbook.acc_group_post.command.application.service.AccGroupPostService;

import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.AccGroupPost;
import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.AccGroupPostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("CommandAccGroupPostController")
@RequestMapping("/acc-group-post")
public class AccGroupPostController {
    private AccGroupPostService accGroupPostService;

    @Autowired
    public AccGroupPostController(AccGroupPostService accGroupPostService) {
        this.accGroupPostService = accGroupPostService;
    }

    @PostMapping("/regist")
    public ResponseEntity<?> registAccGroupPost(@RequestBody AccGroupPost newAccGroupPost) {
        AccGroupPostEntity result = accGroupPostService.registAccGroupPost(newAccGroupPost);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyAccGroupPost(@RequestBody AccGroupPost modifyAccGroupPost) {
        AccGroupPostEntity result = accGroupPostService.modifyAccGroupPost(modifyAccGroupPost);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccGroupPost(@RequestBody AccGroupPost deleteAccGroupPost) {
        accGroupPostService.deleteAccGroupPost(deleteAccGroupPost);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
