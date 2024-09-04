package com.iiiiii.accountbook.community.command.application.service;

import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommnunityFileDTO;
import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommunityPostDTO;
import com.iiiiii.accountbook.community.command.domain.repository.CommunityPostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommunityServiceTests {

    @Autowired
    private CommunityPostService communityPostService;

    @Autowired
    private CommunityFileService communityFileService;

    @Autowired
    private CommunityPostRepository communityPostRepository;

    @DisplayName("새로운 게시글 등록 테스트")
    @Test
    public void registCommunityPost() {

        CommunityPostDTO communityPost =
                new CommunityPostDTO(
                        "2024-02-01 10:10:00", "꿀템 발견!", "이거 보이면 꼭 사세요", 3);

        assertDoesNotThrow(() -> communityPostService.registPost(communityPost));
    }

    @DisplayName("게시글 수정 테스트")
    @Test
    public void modifyCommunityPost() {

        CommunityPostDTO modifiedPost =
                new CommunityPostDTO(
                        6, "2024-02-05 11:00:00", "꿀템 발견!", "단종 됐나봐요..", 3);

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
}
