package com.iiiiii.accountbook.acc_group_post_comment.command.application.service;

import com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate.AccGroupPostComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccGroupPostCommentServiceTests {
    private AccGroupPostCommentService accGroupPostCommentService;

    @Autowired
    public void setAccGroupPostCommentService(AccGroupPostCommentService accGroupPostCommentService) {
        this.accGroupPostCommentService = accGroupPostCommentService;
    }

    @DisplayName("그룹 게시글 댓글 작성 확인 테스트")
    @Test
    public void testRegistGroupPostComment() {
        AccGroupPostComment comment = new AccGroupPostComment("댓글입니다.", 2, 1, 2);
        accGroupPostCommentService.registAccGroupPostComment(comment);
    }

    @DisplayName("그룹 게시글 댓글 작성 확인 테스트")
    @Test
    public void testModifyGroupPostComment() {
        AccGroupPostComment comment = new AccGroupPostComment(11, LocalDateTime.now(), "변경된 댓글입니다.", 2, 1, 2);
        accGroupPostCommentService.modifyAccGroupPostComment(comment);
    }

    @DisplayName("그룹 게시글 댓글 작성 확인 테스트")
    @Test
    public void testDeleteGroupPostComment() {
        AccGroupPostComment comment = new AccGroupPostComment(11, LocalDateTime.parse("2024-09-03 09:59:18"), "변경된 댓글입니다.", 2, 1, 2);
        accGroupPostCommentService.deleteAccGroupPostComment(comment);
    }
}
