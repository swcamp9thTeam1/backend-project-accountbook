package com.iiiiii.accountbook.community.query.repository;

import com.iiiiii.accountbook.community.query.dto.CommunityFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityFileMapper {

    List<CommunityFileDTO> selectFilesOfOneCommunityPost(int postCode);
}
