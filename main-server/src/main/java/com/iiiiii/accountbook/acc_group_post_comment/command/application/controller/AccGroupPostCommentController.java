package com.iiiiii.accountbook.acc_group_post_comment.command.application.controller;

import com.iiiiii.accountbook.acc_group_post_comment.command.application.service.AccGroupPostCommentService;
import com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate.AccGroupPostComment;
import com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate.AccGroupPostCommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> registAccGroupPostComment(@RequestBody AccGroupPostComment newAccGroupPostComment) throws Exception {
        AccGroupPostCommentEntity result = accGroupPostCommentService.registAccGroupPostComment(newAccGroupPostComment);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyAccGroupPostComment(@RequestBody AccGroupPostComment modifyAccGroupPostComment) {
        AccGroupPostCommentEntity result = accGroupPostCommentService.modifyAccGroupPostComment(modifyAccGroupPostComment);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccGroupPostComment(@RequestBody AccGroupPostComment deleteAccGroupPostComment) {
        accGroupPostCommentService.deleteAccGroupPostComment(deleteAccGroupPostComment);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
