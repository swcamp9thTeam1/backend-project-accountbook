package com.iiiiii.accountbook.acc_group_post_comment.query.service;

import com.iiiiii.accountbook.acc_group_post_comment.query.dto.AccGroupPostCommentDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccGroupPostCommentServiceTests {
    private AccGroupPostCommentService accGroupPostCommentService;

    @Autowired
    public void setAccGroupPostCommentService(AccGroupPostCommentService accGroupPostCommentService) {
        this.accGroupPostCommentService = accGroupPostCommentService;
    }

    @DisplayName("그룹 게시글 댓글 조회 확인 테스트")
    @Test
    public void testFindAllAccGroupPostComment() {
        List<AccGroupPostCommentDTO> list = accGroupPostCommentService.findAllGroupPostComment();
        assertNotNull(list);
    }

    @DisplayName("댓글 코드로 그룹 게시글 댓글 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {4, 9})
    public void testFindOneAccGroupPostCommentByCommentCode(int commentCode) {
        AccGroupPostCommentDTO comment = accGroupPostCommentService.findGroupPostCommentByCommentCode(commentCode);
        assertNotNull(comment);
    }

    @DisplayName("멤버 코드로 그룹 게시글 댓글 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 5})
    public void testFindAccGroupPostCommentByMemberCode(int memberCode) {
        List<AccGroupPostCommentDTO> list = accGroupPostCommentService.findGroupPostCommentByMemberCode(memberCode);
        assertNotNull(list);
    }

    @DisplayName("게시글 코드로 그룹 게시글 댓글 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {2, 5})
    public void testFindAccGroupPostCommentByPostCode(int postCode) {
        List<AccGroupPostCommentDTO> list = accGroupPostCommentService.findGroupPostCommentByPostCode(postCode);
        assertNotNull(list);
    }

    @DisplayName("댓글 코드로 그룹 게시글 대댓글 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {4, 5})
    public void testFindAccGroupPostRecommentByCommentCode(int commentCode) {
        AccGroupPostCommentDTO recomment = accGroupPostCommentService.findGroupPostCommentRecomment(commentCode);
        assertNull(recomment);
    }
}
