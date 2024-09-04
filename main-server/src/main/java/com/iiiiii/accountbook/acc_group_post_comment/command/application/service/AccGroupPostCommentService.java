package com.iiiiii.accountbook.acc_group_post_comment.command.application.service;

import com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate.AccGroupPostComment;
import com.iiiiii.accountbook.acc_group_post_comment.command.domain.aggregate.AccGroupPostCommentEntity;
import com.iiiiii.accountbook.acc_group_post_comment.command.domain.repository.AccGroupPostCommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("CommandAccGroupPostCommentService")
public class AccGroupPostCommentService {
    private AccGroupPostCommentRepository accGroupPostCommentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AccGroupPostCommentService(AccGroupPostCommentRepository accGroupPostCommentRepository,
                                      ModelMapper modelMapper) {
        this.accGroupPostCommentRepository = accGroupPostCommentRepository;
        this.modelMapper = modelMapper;
    }

    public void registAccGroupPostComment(AccGroupPostComment newAccGroupPostComment) {
        AccGroupPostCommentEntity comment = modelMapper.map(newAccGroupPostComment, AccGroupPostCommentEntity.class);
        comment.setCreatedAt(LocalDateTime.now());

        accGroupPostCommentRepository.save(comment);
    }


    public void modifyAccGroupPostComment(AccGroupPostComment modifyAccGroupPostComment) {
        AccGroupPostCommentEntity comment = modelMapper.map(modifyAccGroupPostComment, AccGroupPostCommentEntity.class);
        comment.setCreatedAt(LocalDateTime.now());
        accGroupPostCommentRepository.saveAndFlush(comment);
    }


    public void deleteAccGroupPostComment(AccGroupPostComment deleteAccGroupPostComment) {
        AccGroupPostCommentEntity comment = modelMapper.map(deleteAccGroupPostComment, AccGroupPostCommentEntity.class);
        accGroupPostCommentRepository.deleteById(comment.getCode());
    }
}
