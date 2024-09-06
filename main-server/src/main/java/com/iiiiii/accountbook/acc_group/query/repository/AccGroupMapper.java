package com.iiiiii.accountbook.acc_group.query.repository;

import com.iiiiii.accountbook.acc_group.query.dto.AccGroupDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccGroupMapper {
    List<AccGroupDTO> selectAllAccGroups();

    AccGroupDTO selectOneAccGroup(int groupCode);

    List<Integer> selectAllAccGroupCodes();

    List<String> selectAllAccGroupNames();
}
