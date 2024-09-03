package com.iiiiii.accountbook.acc_group_post_file.query.service;

import com.iiiiii.accountbook.acc_group_post_file.query.dto.AccGroupPostFileDTO;
import com.iiiiii.accountbook.acc_group_post_file.query.repository.AccGroupPostFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("QueryAccGroupPostFileService")
public class AccGroupPostFileService {
    private AccGroupPostFileMapper accGroupPostFileMapper;

    @Autowired
    public AccGroupPostFileService(AccGroupPostFileMapper accGroupPostFileMapper) {
        this.accGroupPostFileMapper = accGroupPostFileMapper;
    }

    public List<AccGroupPostFileDTO> findAllGroupPostFile() {
        return accGroupPostFileMapper.selectAllGroupPostFile();
    }

    public AccGroupPostFileDTO findGroupPostFileByFileCode(int fileCode) {
        return accGroupPostFileMapper.selectGroupPostFileByFileCode(fileCode);
    }

    public List<AccGroupPostFileDTO> findGroupPostFileByPostCode(int postCode) {
        return accGroupPostFileMapper.selectGroupPostFileByPostCode(postCode);
    }
}
