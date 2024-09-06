package com.iiiiii.accountbook.community.command.application.service;

import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommunityCommentDTO;
import com.iiiiii.accountbook.community.command.domain.aggregate.entity.CommunityComment;
import com.iiiiii.accountbook.community.command.domain.repository.CommunityCommentRepository;
import com.iiiiii.accountbook.community.command.domain.repository.CommunityPostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CommnunityCommentServiceCommand")
public class CommunityCommentService {

    private final ModelMapper modelMapper;
    private final CommunityCommentRepository communityCommentRepository;
    private final CommunityPostRepository communityPostRepository;

    @Autowired
    public CommunityCommentService(ModelMapper modelMapper,
                                   CommunityCommentRepository communityCommentRepository,
                                   CommunityPostRepository communityPostRepository) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());   // null인 부분은 매핑 제외
        this.communityCommentRepository = communityCommentRepository;
        this.communityPostRepository = communityPostRepository;
    }

    /* 게시글 댓글 및 대댓글 등록 트랜잭션 */
    @Transactional
    public int registComment(Integer postCode, CommunityCommentDTO newComment) {

        if (!communityPostRepository.existsById(postCode)) {
            throw new EntityNotFoundException("존재하지 않는 게시글입니다.");
        } else if (!postCode.equals(newComment.getCommunityPostCode())) {
            throw new IllegalArgumentException("게시글 코드가 일치하지 않습니다.");
        }

        if (newComment.getParentCode() != null) {       // 대댓글인 경우
            if (!communityCommentRepository.existsById(newComment.getParentCode())) {
                throw new EntityNotFoundException("상위 댓글이 존재하지 않습니다.");
            }
        }

        CommunityComment registedComment = communityCommentRepository.save(
                                            modelMapper.map(newComment, CommunityComment.class));

        return registedComment.getCommentCode();
    }

    /* 게시글 댓글 및 대댓글 수정 트랜잭션 */
    @Transactional
    public void modifyComment(Integer postCode, Integer commentCode, CommunityCommentDTO modifiedComment) {

        CommunityComment comment = communityCommentRepository
                                    .findById(commentCode)
                                    .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 댓글입니다."));

        if (!comment.getCommunityPostCode().equals(postCode)) {
            throw new IllegalArgumentException("이 게시글에는 해당 댓글이 존재하지 않습니다.");
        } else if (!commentCode.equals(modifiedComment.getCommentCode())) {
            throw new IllegalArgumentException("댓글 코드가 일치하지 않습니다.");
        }

        if (modifiedComment.getParentCode() != null) {      // 대댓글인 경우
            if (!comment.getParentCode().equals(modifiedComment.getParentCode())) {
                throw new IllegalArgumentException("상위 댓글 코드가 일치하지 않습니다.");
            }
        }

        communityCommentRepository.save(modelMapper.map(modifiedComment, CommunityComment.class));
    }

    /* 게시글 댓글 및 대댓글 삭제 트랜잭션 */
    public void removeComment(Integer postCode, Integer commentCode) {

        if (!communityPostRepository.existsById(postCode))
            throw new EntityNotFoundException("존재하지 않는 게시글입니다.");

        communityCommentRepository.deleteById(commentCode);
    }
}
