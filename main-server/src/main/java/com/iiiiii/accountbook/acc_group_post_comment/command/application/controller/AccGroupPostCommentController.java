package com.iiiiii.accountbook.acc_group_post_comment.command.application.controller;

import com.iiiiii.accountbook.acc_group_post_comment.command.application.service.AccGroupPostCommentService;
import com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate.AccGroupPostComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("CommandAccGroupPostCommentController")
@RequestMapping("/acc-group-post-comment")
public class AccGroupPostCommentController {
    private AccGroupPostCommentService accGroupPostCommentService;

    @Autowired
    public AccGroupPostCommentController(AccGroupPostCommentService accGroupPostCommentService) {
        this.accGroupPostCommentService = accGroupPostCommentService;
    }

    @PostMapping("/regist")
    public ResponseEntity<?> registAccGroupPostComment(@RequestBody AccGroupPostComment newAccGroupPostComment) {
        accGroupPostCommentService.registAccGroupPostComment(newAccGroupPostComment);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyAccGroupPostComment(@RequestBody AccGroupPostComment modifyAccGroupPostComment) {
        accGroupPostCommentService.modifyAccGroupPostComment(modifyAccGroupPostComment);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccGroupPostComment(@RequestBody AccGroupPostComment deleteAccGroupPostComment) {
        accGroupPostCommentService.deleteAccGroupPostComment(deleteAccGroupPostComment);

        return ResponseEntity
                .noContent()
                .build();
    }
}
