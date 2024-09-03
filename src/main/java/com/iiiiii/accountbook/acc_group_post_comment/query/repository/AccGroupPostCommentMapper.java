package com.iiiiii.accountbook.acc_group_post_comment.query.repository;

import com.iiiiii.accountbook.acc_group_post_comment.query.dto.AccGroupPostCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccGroupPostCommentMapper {
    List<AccGroupPostCommentDTO> selectAllGroupPostComment();

    AccGroupPostCommentDTO selectGroupPostCommentByCommentCode(int accGroupPostCommentCode);

    AccGroupPostCommentDTO selectGroupPostCommentRecomment(int accGroupPostCommentCode);

    List<AccGroupPostCommentDTO> selectGroupPostCommentByPostCode(int postCode);

    List<AccGroupPostCommentDTO> selectGroupPostCommentByMemberCode(int memberCode);
}
