package com.iiiiii.accountbook.community.query.service;

import com.iiiiii.accountbook.community.query.dto.CommunityCommentDTO;
import com.iiiiii.accountbook.community.query.repository.CommunityCommentMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CommunityCommentServiceTests {

    @Mock
    private CommunityCommentMapper communityCommentMapper;

    @InjectMocks
    private CommunityCommentService communityCommentService;

    private static final List<CommunityCommentDTO> comments = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        comments.add(new CommunityCommentDTO(1, LocalDateTime.parse("2024-01-01T12:10:00"),
                "좋은 정보 감사합니다ㅎㅎ!", 1, 2, null));
        comments.add(new CommunityCommentDTO(2, LocalDateTime.parse("2024-01-01T12:12:00"),
                "와우 몰랐던 혜택이네요..", 1, 4, null));
        comments.add(new CommunityCommentDTO(3, LocalDateTime.parse("2024-01-01T12:11:00"),
                "넵ㅎㅎ", 1, 1, 1));
    }

    @DisplayName("커뮤니티 게시글의 댓글 목록 조회 테스트")
    @Test
    public void testFindAllCommunityCommentsByCommunityPostCode() {

        // given
        int postCode = 1;
        given(communityCommentService.findCommentsOfCommunityPost(postCode)).willReturn(comments);

        // when
        List<CommunityCommentDTO> foundComments = communityCommentService.findCommentsOfCommunityPost(postCode);

        // then
        assertThat(foundComments.size()).isEqualTo(comments.size());
    }

}
