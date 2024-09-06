package com.iiiiii.accountbook.acc_group_post_comment.query.controller;

import com.iiiiii.accountbook.acc_group_post_comment.query.dto.AccGroupPostCommentDTO;
import com.iiiiii.accountbook.acc_group_post_comment.query.service.AccGroupPostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("QueryAccGroupPostCommentController")
@RequestMapping("/acc-group-post-comment")
public class AccGroupPostCommentController {
    private AccGroupPostCommentService accGroupPostCommentService;

    @Autowired
    public AccGroupPostCommentController(AccGroupPostCommentService accGroupPostCommentService) {
        this.accGroupPostCommentService = accGroupPostCommentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AccGroupPostCommentDTO>> findAllGroupPostComment() {
        List<AccGroupPostCommentDTO> groupPostComment = accGroupPostCommentService.findAllGroupPostComment();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupPostComment);
    }

    @GetMapping("/{accGroupPostCommentCode}")
    public ResponseEntity<AccGroupPostCommentDTO> findGroupPostCommentByCommentCode(@PathVariable int accGroupPostCommentCode) {
        AccGroupPostCommentDTO groupPostComment = accGroupPostCommentService.findGroupPostCommentByCommentCode(accGroupPostCommentCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupPostComment);
    }

    @GetMapping("/recomment/{accGroupPostCommentCode}")
    public ResponseEntity<AccGroupPostCommentDTO> findGroupPostCommentRecomment(@PathVariable int accGroupPostCommentCode) {
        AccGroupPostCommentDTO groupPostComment = accGroupPostCommentService.findGroupPostCommentRecomment(accGroupPostCommentCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupPostComment);
    }

    @GetMapping("/posts/{postCode}")
    public ResponseEntity<List<AccGroupPostCommentDTO>> findGroupPostCommentByPostCode(@PathVariable int postCode) {
        List<AccGroupPostCommentDTO> groupPostComment = accGroupPostCommentService.findGroupPostCommentByPostCode(postCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupPostComment);
    }

    @GetMapping("/members/{memberCode}")
    public ResponseEntity<List<AccGroupPostCommentDTO>> findGroupPostCommentByMemberCode(@PathVariable int memberCode) {
        List<AccGroupPostCommentDTO> groupPostComment = accGroupPostCommentService.findGroupPostCommentByMemberCode(memberCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupPostComment);
    }
}
