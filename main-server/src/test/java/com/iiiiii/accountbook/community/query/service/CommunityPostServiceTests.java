package com.iiiiii.accountbook.community.query.service;

import com.iiiiii.accountbook.community.query.dto.CommunityPostDTO;
import com.iiiiii.accountbook.community.query.repository.CommunityPostMapper;
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
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/*
 * 테스트 코드 작성 시 참고한 글
 * - https://lemontia.tistory.com/915
 * - https://thisisprogrammingworld.tistory.com/172
 * - https://g-db.tistory.com/entry/Spring-Test-Mockito-when%EA%B3%BC-given-%EC%B0%A8%EC%9D%B4
 */

@ExtendWith(MockitoExtension.class)
public class CommunityPostServiceTests {

    @Mock
    private CommunityPostMapper communityPostMapper;    // CommunityPostService 사용 시 주입되어야 하는 Mapper는
                                                        // @Mock을 통해 주입
    @InjectMocks
    private CommunityPostService communityPostService;

    private static final List<CommunityPostDTO> postList = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        postList.add(new CommunityPostDTO(1, LocalDateTime.parse("2024-01-01T12:00:00"),
                "가성비 식당 공유", "여기 대박입니다.", 1));
        postList.add(new CommunityPostDTO(2, LocalDateTime.parse("2024-01-02T13:00:00"),
                "가성비 숙소 공유", "시설 완전 좋아요!", 1));
        postList.add(new CommunityPostDTO(3, LocalDateTime.parse("2024-01-03T14:00:00"),
                "카페 할인 이벤트", "답십리역 새로 생긴 카페 할인한대요!", 2));
        postList.add(new CommunityPostDTO(4, LocalDateTime.parse("2024-01-04T15:00:00"),
                "지출 줄이는 법", "어떻게 아까시나요 다들...", 2));
        postList.add(new CommunityPostDTO(5, LocalDateTime.parse("2024-01-05T16:00:00"),
                "특판 적금 떴어요!", "얼른 신청하세요~", 3));
    }

    @DisplayName("커뮤니티 게시글 목록 조회 테스트")
    @Test
    public void selectAllCommunityPosts() {

        // given
        given(communityPostService.findAllCommunityPosts()).willReturn(postList);

        // when
        List<CommunityPostDTO> foundPosts = communityPostService.findAllCommunityPosts();

        // then
        assertThat(foundPosts.size()).isEqualTo(postList.size());
    }

    @DisplayName("커뮤니티 게시글 1개 조회 테스트")
    @Test
    public void testSelectOneCommunityPost() {

        // given
        int postCode = 1;
        given(communityPostService.findOneCommunityPost(postCode))
                .willReturn(postList.stream().filter(post -> post.getPostCode() == postCode)
                                             .collect(Collectors.toList())
                                             .get(0));

        // when
        CommunityPostDTO foundPost = communityPostService.findOneCommunityPost(postCode);

        // then
        assertThat(foundPost.getPostCode()).isEqualTo(postCode);

    }

    @DisplayName("존재하지 않는 커뮤니티 게시글 1개 조회 테스트")
    @Test
    public void testSelectOneNotFoundCommunityPost() {

        // given
        int postCode = 10;
        given(communityPostService.findOneCommunityPost(postCode)).willReturn(null);

        // when
        CommunityPostDTO foundPost = communityPostService.findOneCommunityPost(postCode);

        // then
        assertThat(foundPost).isNull();
    }

}
