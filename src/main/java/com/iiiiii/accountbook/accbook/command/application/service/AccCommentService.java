package com.iiiiii.accountbook.accbook.command.application.service;

import com.iiiiii.accountbook.accbook.command.domain.aggregate.dto.AccCommentDTO;
import com.iiiiii.accountbook.accbook.command.domain.aggregate.entity.AccComment;
import com.iiiiii.accountbook.accbook.command.domain.repository.AccCommentRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AccCommentServiceCommand")
@Slf4j
public class AccCommentService {

    private final AccCommentRepository accCommentRepository;

    @Autowired
    public AccCommentService(AccCommentRepository accCommentRepository) {
        this.accCommentRepository = accCommentRepository;
    }
    public AccComment registAccbookComment(AccCommentDTO newAccCommentDTO) {

        AccComment accComment = new AccComment();

        accComment.setCreatedAt(newAccCommentDTO.getCreatedAt());
        accComment.setDetail(newAccCommentDTO.getDetail());
        accComment.setParentCode(newAccCommentDTO.getParentCode());
        accComment.setAccbookCode(newAccCommentDTO.getAccbookCode());
        accComment.setMemberCode(newAccCommentDTO.getMemberCode());

        accCommentRepository.save(accComment);
        return accComment;
    }
}
