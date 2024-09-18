package com.iiiiii.accountbook.community.query.service;

import com.iiiiii.accountbook.community.query.dto.CommunityPostScrapDTO;
import com.iiiiii.accountbook.community.query.repository.CommunityPostScrapMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityPostScrapService {

    private final CommunityPostScrapMapper communityPostScrapMapper;

    public CommunityPostScrapService(CommunityPostScrapMapper communityPostScrapMapper) {
        this.communityPostScrapMapper = communityPostScrapMapper;
    }

    /* 회원 본인이 스크랩한 커뮤니티 게시글 목록 조회 */
    public List<CommunityPostScrapDTO> findPostScrapOfMember(int memberCode) {

        List<CommunityPostScrapDTO> postScrapList = communityPostScrapMapper.selectPostScrapByMemberCode(memberCode);

        return postScrapList;
    }
}
