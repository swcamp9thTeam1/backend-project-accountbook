package com.iiiiii.accountbook.community.command.application.service;

import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommnunityFileDTO;
import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommunityPostDTO;
import com.iiiiii.accountbook.community.command.domain.aggregate.entity.CommunityFile;
import com.iiiiii.accountbook.community.command.domain.aggregate.entity.CommunityPost;
import com.iiiiii.accountbook.community.command.domain.repository.CommunityPostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CommunityPostServiceCommand")
public class CommunityPostService {

    private final ModelMapper modelMapper;
    private final CommunityPostRepository communityPostRepository;

    @Autowired
    public CommunityPostService(ModelMapper modelMapper, CommunityPostRepository communityPostRepository) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());   // null인 부분은 매핑 제외
        this.communityPostRepository = communityPostRepository;
    }

    /* 게시글 등록 트랜잭션 */
    @Transactional
    public int registPost(CommunityPostDTO newPost) {

        CommunityPost registedPost = communityPostRepository.save(modelMapper.map(newPost, CommunityPost.class));

        return registedPost.getPostCode();
    }

    /* 게시글 수정 트랜잭션 */
    // 제목(title), 내용(detail) 수정 가능
    @Transactional
    public void modifyPost(Integer postCode, CommunityPostDTO modifiedPost) {

        CommunityPost myPost = communityPostRepository.findById(postCode)
                .orElseThrow(() -> new EntityNotFoundException("해당 코드의 게시글은 존재하지 않습니다."));

        if (myPost.getPostCode() != modifiedPost.getPostCode()) {
            throw new IllegalArgumentException("게시글 코드가 일치하지 않습니다.");
        } else if (myPost.getMemberCode() != modifiedPost.getMemberCode()) {
            throw new IllegalArgumentException("작성자 본인이 아니라면 수정이 불가합니다.");
        }

        communityPostRepository.save(modelMapper.map(modifiedPost, CommunityPost.class));
    }

    /* 게시글 삭제 트랜잭션 */
    @Transactional
    public void removePost(Integer postCode) {
        communityPostRepository.deleteById(postCode);
    }
}
