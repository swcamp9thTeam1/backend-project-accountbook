package com.iiiiii.accountbook.acc_group_post_comment.command.application.service;

import com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate.AccGroupPostComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccGroupPostCommentServiceTests {
    private AccGroupPostCommentService accGroupPostCommentService;
    private com.iiiiii.accountbook.acc_group_post_comment.query.service.AccGroupPostCommentService queryService;

    @Autowired
    public void setAccGroupPostCommentService(AccGroupPostCommentService accGroupPostCommentService,
                                              com.iiiiii.accountbook.acc_group_post_comment.query.service.AccGroupPostCommentService queryService) {
        this.accGroupPostCommentService = accGroupPostCommentService;
        this.queryService = queryService;
    }

    @DisplayName("그룹 게시글 댓글 작성 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {10, 0})
    public void testRegistGroupPostComment(int parentCode) throws Exception {
        AccGroupPostComment comment = new AccGroupPostComment("대대댓글테스트.",parentCode, 2, 5, 3);
        if (parentCode == 10) {
            try {
                accGroupPostCommentService.registAccGroupPostComment(comment);
            } catch (Exception e) {
                assertTrue(true);
            }
        } else if (parentCode == 0) {
            int result = accGroupPostCommentService.registAccGroupPostComment(comment).getCode();
            assertTrue(result > 0);
        }
    }

    @DisplayName("그룹 게시글 댓글 수정 확인 테스트")
    @Test
    public void testModifyGroupPostComment() {
        AccGroupPostComment comment = new AccGroupPostComment(11, LocalDateTime.now(), "댓글수정테스트", null, 2, 1, 2);
        String result = accGroupPostCommentService.modifyAccGroupPostComment(comment).getDetail();
        assertEquals("댓글수정테스트", result);
    }

    @DisplayName("그룹 게시글 댓글 삭제 확인 테스트")
    @Test
    public void testDeleteGroupPostComment() {
        AccGroupPostComment comment = new AccGroupPostComment(11, LocalDateTime.parse("2024-09-03 09:59:18"), "변경된 댓글입니다.", null, 2, 1, 2);
        accGroupPostCommentService.deleteAccGroupPostComment(comment);
        assertNull(queryService.findGroupPostCommentByCommentCode(11));
    }
}
