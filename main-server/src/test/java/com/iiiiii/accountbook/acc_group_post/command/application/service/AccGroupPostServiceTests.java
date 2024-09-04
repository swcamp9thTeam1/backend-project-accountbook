package com.iiiiii.accountbook.acc_group_post.command.application.service;

import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.AccGroupPost;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccGroupPostServiceTests {
    private AccGroupPostService accGroupPostService;

    @Autowired
    public void setAccGroupPostService(AccGroupPostService accGroupPostService) {
        this.accGroupPostService = accGroupPostService;
    }

    @DisplayName("그룹 게시글 작성 확인 테스트")
    @Test
    public void testRegistAccGroupPost() {
        AccGroupPost newPost = new AccGroupPost("테스트 게시글", "테스트 게시글 작성 내용", 2, 3);
        accGroupPostService.registAccGroupPost(newPost);
    }

    @DisplayName("그룹 게시글 수정 확인 테스트")
    @Test
    public void testModifyAccGroupPost() {
        LocalDateTime now = LocalDateTime.now();
        AccGroupPost modifyPost = new AccGroupPost(2, now, "테스트 게시글", "테스트 게시글 작성 내용", 2, 3);
        accGroupPostService.modifyAccGroupPost(modifyPost);
    }

    @DisplayName("그룹 게시글 삭제 확인 테스트")
    @Test
    public void testDeleteAccGroupPost() {
        String date = "2024-08-27 14:42:49";
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        AccGroupPost deletePost = new AccGroupPost(3, localDateTime, "제목3", "상세내용3", 3, 2);
        accGroupPostService.deleteAccGroupPost(deletePost);
    }
}
