package com.iiiiii.accountbook.community.query.service;

import com.iiiiii.accountbook.community.query.dto.CommunityPostScrapDTO;
import com.iiiiii.accountbook.community.query.repository.CommunityPostScrapMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CommunityPostScrapServiceTests {

    @Mock
    private CommunityPostScrapMapper communityPostScrapMapper;

    @InjectMocks
    private CommunityPostScrapService communityPostScrapService;

    private static final List<CommunityPostScrapDTO> postScrapList = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        postScrapList.add(new CommunityPostScrapDTO(9, 1));
        postScrapList.add(new CommunityPostScrapDTO(9, 3));
        postScrapList.add(new CommunityPostScrapDTO(9, 5));
        postScrapList.add(new CommunityPostScrapDTO(8, 1));
        postScrapList.add(new CommunityPostScrapDTO(8, 3));
    }

    @DisplayName("회원이 스크랩한 게시글 목록 조회 테스트")
    @Test
    public void selectPostScrapOfMember() {

        // given
        int memberCode = 9;
        given(communityPostScrapService.findPostScrapOfMember(memberCode)).willReturn(
                        postScrapList.stream().filter(scrap -> scrap.getMemberCode() == memberCode)
                                    .collect(Collectors.toList())
        );

        // when
        List<CommunityPostScrapDTO> myScrapList = communityPostScrapService.findPostScrapOfMember(memberCode);

        //then
        assertEquals(3, myScrapList.size());
    }
}
