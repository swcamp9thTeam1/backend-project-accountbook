package com.iiiiii.accountbook.accbook.command.application.service;

import com.iiiiii.accountbook.accbook.command.domain.aggregate.dto.CreateAccCommentDTO;
import com.iiiiii.accountbook.accbook.command.domain.aggregate.dto.UpdateAccCommentDTO;
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
    public AccComment registAccbookComment(Integer accbookCode, CreateAccCommentDTO newAccCommentDTO) {

        AccComment accComment = new AccComment();

        accComment.setAccbookCode(accbookCode);
        accComment.setCreatedAt(newAccCommentDTO.getCreatedAt());
        accComment.setDetail(newAccCommentDTO.getDetail());
        accComment.setParentCode(newAccCommentDTO.getParentCode());
        accComment.setMemberCode(newAccCommentDTO.getMemberCode());

        accCommentRepository.save(accComment);
        return accComment;
    }

    @Transactional
    public AccComment modifyAccComment(Integer accCommentCode, UpdateAccCommentDTO modifyAccComment) {

        AccComment accComment = accCommentRepository.findById(accCommentCode).orElseThrow(IllegalArgumentException::new);

        accComment.setDetail(modifyAccComment.getDetail());

        return accComment;
    }

    @Transactional
    public void removeAccComment(Integer accCommentCode) {
        AccComment accComment = accCommentRepository.findById(accCommentCode).orElseThrow(IllegalArgumentException::new);
        accCommentRepository.delete(accComment);
    }
}
