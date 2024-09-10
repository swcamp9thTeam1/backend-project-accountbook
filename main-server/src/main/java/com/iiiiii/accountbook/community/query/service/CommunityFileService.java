package com.iiiiii.accountbook.community.query.service;

import com.iiiiii.accountbook.community.query.dto.CommunityFileDTO;
import com.iiiiii.accountbook.community.query.repository.CommunityFileMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityFileService {

    private final CommunityFileMapper communityFileMapper;

    public CommunityFileService(CommunityFileMapper communityFileMapper) {
        this.communityFileMapper = communityFileMapper;
    }

    /* 한 게시글에 첨부된 파일들 조회 */
    public List<CommunityFileDTO> findFilesOfCommunityPost(int postCode) {

        List<CommunityFileDTO> fileListOfOnePost = communityFileMapper.selectFilesOfOneCommunityPost(postCode);

        return fileListOfOnePost;
    }
}
