package com.iiiiii.accountbook.acc_group.query.repository;

import com.iiiiii.accountbook.acc_group.query.dto.QueryAccGroupDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QueryAccGroupMapper {
    List<QueryAccGroupDTO> selectAllAccGroups();

    QueryAccGroupDTO selectOneAccGroup(int code);

    List<Integer> selectAllAccGroupCodes();

    List<String> selectAllAccGroupNames();
}
