package com.iiiiii.accountbook.community.command.application.service;

import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommnunityFileDTO;
import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommunityCommentDTO;
import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommunityPostDTO;
import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommunityPostScrapDTO;
import com.iiiiii.accountbook.community.command.domain.repository.CommunityPostScrapRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommunityServiceTests {

    @Autowired
    private CommunityPostService communityPostService;

    @Autowired
    private CommunityFileService communityFileService;

    @Autowired
    private CommunityCommentService communityCommentService;

    @Autowired
    private CommunityPostScrapService communityPostScrapService;

    @Autowired
    private CommunityPostScrapRepository communityPostScrapRepository;

    private static Stream<Arguments> provideMemberCodeAndPostCode() {
        return Stream.of(
                Arguments.of(3, 2)
        );
    }

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

    @DisplayName("게시글 스크랩 테스트")
    @ParameterizedTest
    @MethodSource("provideMemberCodeAndPostCode")
    public void addCommunityPostScrap(int memberCode, int postCode) {

        CommunityPostScrapDTO newScrap = new CommunityPostScrapDTO(memberCode, postCode);

        int scrapMemberCode = communityPostScrapService.addScrap(postCode, newScrap);

        assertEquals(memberCode, scrapMemberCode);
    }

    @DisplayName("스크랩했던 게시글 스크랩 취소(삭제) 테스트")
    @ParameterizedTest
    @MethodSource("provideMemberCodeAndPostCode")
    public void cancelCommunityPostScrap(int memberCode, int postCode) {

        Long initCount = communityPostScrapRepository.count();

        CommunityPostScrapDTO scrap = new CommunityPostScrapDTO(memberCode, postCode);
        communityPostScrapService.cancelScrap(scrap);

        assertNotEquals(initCount, communityPostScrapRepository.count());
    }
}
