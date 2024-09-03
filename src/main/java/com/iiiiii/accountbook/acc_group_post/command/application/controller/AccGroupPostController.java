package com.iiiiii.accountbook.acc_group_post.command.application.controller;

import com.iiiiii.accountbook.acc_group_post.command.application.service.AccGroupPostService;
import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.AccGroupPost;
import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.AccGroupPostEntity;
import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.AccGroupPostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        accGroupPostService.registAccGroupPost(newAccGroupPost);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyAccGroupPost(@RequestBody AccGroupPostVO modifyAccGroupPost) {
        accGroupPostService.modifyAccGroupPost(modifyAccGroupPost);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteAccGroupPost(@RequestBody AccGroupPostVO deleteAccGroupPost) {
        accGroupPostService.deleteAccGroupPost(deleteAccGroupPost);

        return ResponseEntity
                .noContent()
                .build();
    }
}
