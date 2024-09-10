package com.iiiiii.accountbook.community.query.service;

import com.iiiiii.accountbook.community.query.dto.CommunityPostDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommunityServiceTests {

    @Autowired
    private CommunityPostService communityPostService;

    @Autowired
    private CommunityFileService communityFileService;

    @Autowired
    private CommunityCommentService communityCommentService;

    @DisplayName("커뮤니티 게시글 목록 조회 테스트")
    @Test
    public void selectAllCommunityPosts() {
        List<CommunityPostDTO> postList = communityPostService.findAllCommunityPosts();
        int finalPostCode = postList.size();

        assertEquals(5, finalPostCode);
    }

}
