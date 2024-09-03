package com.iiiiii.accountbook.acc_group_post_comment.command.application.controller;

import com.iiiiii.accountbook.acc_group_post_comment.command.application.service.AccGroupPostCommentService;
import com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate.AccGroupPostComment;
import com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate.AccGroupPostCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> modifyAccGroupPostComment(@RequestBody AccGroupPostCommentVO modifyAccGroupPostComment) {
        accGroupPostCommentService.modifyAccGroupPostComment(modifyAccGroupPostComment);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteAccGroupPostComment(@RequestBody AccGroupPostCommentVO deleteAccGroupPostComment) {
        accGroupPostCommentService.deleteAccGroupPostComment(deleteAccGroupPostComment);

        return ResponseEntity
                .noContent()
                .build();
    }
}
