package com.iiiiii.accountbook.acc_group_post.query.repository;

import com.iiiiii.accountbook.acc_group_post.query.dto.AccGroupPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccGroupPostMapper {
    List<AccGroupPostDTO> selectAllGroupPost();

    AccGroupPostDTO selectGroupPostByGroupPostCode(int groupPostCode);

    List<AccGroupPostDTO> selectGroupPostByGroupCode(int groupCode);

    List<AccGroupPostDTO> selectGroupPostByMemberCode(int memberCode);

}
