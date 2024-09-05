package com.iiiiii.accountbook.community.query.repository;

import com.iiiiii.accountbook.community.query.dto.CommunityPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityPostMapper {

    List<CommunityPostDTO> selectAllCommunityPosts();

    CommunityPostDTO selectOneCommunityPost(int postCode);
}
