package com.iiiiii.accountbook.community.command.application.service;

import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommnunityFileDTO;
import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommunityCommentDTO;
import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommunityPostDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommunityServiceTests {

    @Autowired
    private CommunityPostService communityPostService;

    @Autowired
    private CommunityFileService communityFileService;

    @Autowired
    private CommunityCommentService communityCommentService;

    @DisplayName("새로운 게시글 등록 테스트")
    @Test
    public void registCommunityPost() {

        CommunityPostDTO communityPost =
                new CommunityPostDTO(
                        LocalDateTime.now(), "꿀템 발견!", "이거 보이면 꼭 사세요", 3);

        assertDoesNotThrow(() -> communityPostService.registPost(communityPost));
    }

    @DisplayName("게시글 수정 테스트")
    @Test
    public void modifyCommunityPost() {

        CommunityPostDTO modifiedPost =
                new CommunityPostDTO(6, LocalDateTime.now(),
                        "꿀템 발견!", "단종 됐나봐요..", 3);

        assertDoesNotThrow(() -> communityPostService.modifyPost(6, modifiedPost));
    }

    @DisplayName("게시글 삭제 테스트")
    @Test
    public void removeCommunityPost() {

        assertDoesNotThrow(() -> communityPostService.removePost(6));
    }

    @DisplayName("게시글 첨부파일 등록 테스트")
    @Test
    public void registCommunityFile() {

        CommnunityFileDTO newFile = new CommnunityFileDTO(
                "칼국수.jpg", "src/main/resources/static", 1);

        assertDoesNotThrow(() -> communityFileService.registFile(1, newFile));
    }

    @DisplayName("게시글 첨부파일 수정 테스트")
    @Test
    public void modifyCommunityFile() {

        CommnunityFileDTO modifiedFile = new CommnunityFileDTO(
                1, "마라탕.jpg", "src/main/resources/static", 1);

        assertDoesNotThrow(() -> communityFileService.modifyFile(1, 1, modifiedFile));
    }

    @DisplayName("게시글 첨부파일 삭제 테스트")
    @Test
    public void removeCommunityFile() {
        assertDoesNotThrow(() -> communityFileService.removeFile(1, 1));
    }

    @DisplayName("게시글 댓글 등록 테스트")
    @Test
    public void registCommunityComment() {

        CommunityCommentDTO newComment =
                new CommunityCommentDTO(LocalDateTime.now(), "우왕 감사합니다!",
                        2, 9, null);

        assertDoesNotThrow(() -> communityCommentService.registComment(2, newComment));
    }

    @DisplayName("게시글 댓글 수정 테스트")
    @Test
    public void modifyCommunityComment() {

        CommunityCommentDTO modifiedComment =
                new CommunityCommentDTO(1, LocalDateTime.now(),
                        "저도 가본 곳이네요!", 2, 9, null);

        assertDoesNotThrow(() -> communityCommentService.modifyComment(2, 1, modifiedComment));
    }

    @DisplayName("게시글 댓글 삭제 테스트")
    @Test
    public void removeCommunityComment() {
        assertDoesNotThrow(() -> communityCommentService.removeComment(2, 1));
    }
}
