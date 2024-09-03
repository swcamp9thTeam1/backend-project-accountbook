package com.iiiiii.accountbook.acc_group_post_file.query.repository;

import com.iiiiii.accountbook.acc_group_post_file.query.dto.AccGroupPostFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccGroupPostFileMapper {
    List<AccGroupPostFileDTO> selectAllGroupPostFile();

    AccGroupPostFileDTO selectGroupPostFileByFileCode(int fileCode);

    List<AccGroupPostFileDTO> selectGroupPostFileByPostCode(int postCode);
}
