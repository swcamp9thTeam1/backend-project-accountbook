package com.iiiiii.accountbook.accbook.command.application.service;

import com.iiiiii.accountbook.accbook.command.domain.aggregate.dto.AccCommentDTO;
import com.iiiiii.accountbook.accbook.command.domain.aggregate.entity.AccComment;
import com.iiiiii.accountbook.accbook.command.domain.repository.AccCommentRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("AccCommentServiceCommand")
@Slf4j
public class AccCommentService {

    private final AccCommentRepository accCommentRepository;

    @Autowired
    public AccCommentService(AccCommentRepository accCommentRepository) {
        this.accCommentRepository = accCommentRepository;
    }

    @Transactional
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

    @Transactional
    public AccComment modifyAccComment(int accCommentCode, AccCommentDTO modifyAccComment) {

        AccComment accComment = accCommentRepository.findById(accCommentCode).orElseThrow(IllegalArgumentException::new);

        accComment.setCreatedAt(modifyAccComment.getCreatedAt());
        accComment.setDetail(modifyAccComment.getDetail());
        accComment.setParentCode(modifyAccComment.getParentCode());
        accComment.setAccbookCode(modifyAccComment.getAccbookCode());
        accComment.setMemberCode(modifyAccComment.getMemberCode());

        return accComment;
    }

    @Transactional
    public void removeAccComment(int accCommentCode) {
        AccComment accComment = accCommentRepository.findById(accCommentCode).orElseThrow(IllegalArgumentException::new);
        accCommentRepository.delete(accComment);
    }
}
