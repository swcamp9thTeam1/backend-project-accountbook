package com.iiiiii.accountbook.community.query.repository;

import com.iiiiii.accountbook.community.query.dto.CommunityPostScrapDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityPostScrapMapper {

    List<CommunityPostScrapDTO> selectPostScrapByMemberCode(int memberCode);
}
