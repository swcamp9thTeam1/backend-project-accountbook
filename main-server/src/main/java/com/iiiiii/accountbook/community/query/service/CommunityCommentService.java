package com.iiiiii.accountbook.community.query.service;

import com.iiiiii.accountbook.community.query.dto.CommunityCommentDTO;
import com.iiiiii.accountbook.community.query.repository.CommunityCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityCommentService {

    private final CommunityCommentMapper communityCommentMapper;

    public CommunityCommentService(CommunityCommentMapper communityCommentMapper) {
        this.communityCommentMapper = communityCommentMapper;
    }

    /* 한 게시글의 댓글 목록 조회 */
    public List<CommunityCommentDTO> findCommentsOfCommunityPost(int postCode) {

        List<CommunityCommentDTO> commentListOfOnePost =
                                    communityCommentMapper.selectCommentsOfOneCommunityPost(postCode);

        return commentListOfOnePost;
    }
}
