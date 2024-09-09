package com.iiiiii.accountbook.community.query.repository;

import com.iiiiii.accountbook.community.query.dto.CommunityCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityCommentMapper {

    List<CommunityCommentDTO> selectCommentsOfOneCommunityPost(int postCode);
}
