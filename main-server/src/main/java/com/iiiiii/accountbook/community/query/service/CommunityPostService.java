package com.iiiiii.accountbook.community.query.service;

import com.iiiiii.accountbook.community.query.dto.CommunityPostDTO;
import com.iiiiii.accountbook.community.query.repository.CommunityPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityPostService {

    @Autowired
    private CommunityPostMapper communityPostMapper;

    /* 커뮤니티 게시글 목록 조회 */
    public List<CommunityPostDTO> findAllCommunityPosts() {

        List<CommunityPostDTO> postList = communityPostMapper.selectAllCommunityPosts();

        return postList;
    }

    /* 커뮤니티 게시글 1개 상세 조회 */
    public CommunityPostDTO findOneCommunityPost(int postCode) {

        CommunityPostDTO selectedPost = communityPostMapper.selectOneCommunityPost(postCode);

        return selectedPost;
    }
}
