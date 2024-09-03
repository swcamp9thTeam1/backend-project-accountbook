package com.iiiiii.accountbook.acc_group_post_comment.query.service;

import com.iiiiii.accountbook.acc_group_post_comment.query.dto.AccGroupPostCommentDTO;
import com.iiiiii.accountbook.acc_group_post_comment.query.repository.AccGroupPostCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("QueryAccGroupPostCommentService")
public class AccGroupPostCommentService {
    private AccGroupPostCommentMapper accGroupPostCommentMapper;

    @Autowired
    public AccGroupPostCommentService(AccGroupPostCommentMapper accGroupPostCommentMapper) {
        this.accGroupPostCommentMapper = accGroupPostCommentMapper;
    }

    public List<AccGroupPostCommentDTO> findAllGroupPostComment() {
        return accGroupPostCommentMapper.selectAllGroupPostComment();
    }

    public AccGroupPostCommentDTO findGroupPostCommentByCommentCode(int accGroupPostCommentCode) {
        return accGroupPostCommentMapper.selectGroupPostCommentByCommentCode(accGroupPostCommentCode);
    }

    public AccGroupPostCommentDTO findGroupPostCommentRecomment(int accGroupPostCommentCode) {
        return accGroupPostCommentMapper.selectGroupPostCommentRecomment(accGroupPostCommentCode);
    }

    public List<AccGroupPostCommentDTO> findGroupPostCommentByPostCode(int postCode) {
        return accGroupPostCommentMapper.selectGroupPostCommentByPostCode(postCode);
    }

    public List<AccGroupPostCommentDTO> findGroupPostCommentByMemberCode(int memberCode) {
        return accGroupPostCommentMapper.selectGroupPostCommentByMemberCode(memberCode);
    }
}
