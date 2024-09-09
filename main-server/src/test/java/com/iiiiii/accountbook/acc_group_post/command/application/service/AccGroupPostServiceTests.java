package com.iiiiii.accountbook.acc_group_post.command.application.service;

import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.AccGroupPost;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccGroupPostServiceTests {
    private AccGroupPostService accGroupPostService;
    private com.iiiiii.accountbook.acc_group_post.query.service.AccGroupPostService queryService;

    @Autowired
    public void setAccGroupPostService(AccGroupPostService accGroupPostService,
                                       com.iiiiii.accountbook.acc_group_post.query.service.AccGroupPostService queryService) {
        this.accGroupPostService = accGroupPostService;
        this.queryService = queryService;
    }

    @DisplayName("그룹 게시글 작성 확인 테스트")
    @Test
    public void testRegistAccGroupPost() {
        AccGroupPost newPost = new AccGroupPost("테스트 게시글", "테스트 게시글 작성 내용", 3, 2);
        int result = accGroupPostService.registAccGroupPost(newPost).getCode();
        Assertions.assertTrue(result > 0);
    }

    @DisplayName("그룹 게시글 수정 확인 테스트")
    @Test
    public void testModifyAccGroupPost() {
        AccGroupPost modifyPost = new AccGroupPost(2, LocalDateTime.now(), "게시글 수정 테스트", "테스트 게시글 작성 내용", 2, 1);
        String result = accGroupPostService.modifyAccGroupPost(modifyPost).getTitle();
        Assertions.assertEquals("게시글 수정 테스트", result);
    }

    @DisplayName("그룹 게시글 삭제 확인 테스트")
    @Test
    public void testDeleteAccGroupPost() {
        AccGroupPost deletePost = new AccGroupPost(4, LocalDateTime.parse("2024-08-04 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Post Title 4", "Post detail 4", 4, 2);
        accGroupPostService.deleteAccGroupPost(deletePost);
        Assertions.assertNull(queryService.findGroupPostByGroupPostCode(4));
    }
}
