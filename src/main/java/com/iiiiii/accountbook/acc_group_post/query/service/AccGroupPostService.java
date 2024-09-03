package com.iiiiii.accountbook.acc_group_post.query.service;

import com.iiiiii.accountbook.acc_group_post.query.dto.AccGroupPostDTO;
import com.iiiiii.accountbook.acc_group_post.query.repository.AccGroupPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("QueryAccGroupPostService")
public class AccGroupPostService {
    private AccGroupPostMapper accGroupPostMapper;

    @Autowired
    public AccGroupPostService(AccGroupPostMapper accGroupPostMapper) {
        this.accGroupPostMapper = accGroupPostMapper;
    }

    public List<AccGroupPostDTO> findAllGroupPost() {
        return accGroupPostMapper.selectAllGroupPost();
    }

    public AccGroupPostDTO findGroupPostByGroupPostCode(int groupPostCode) {
        return accGroupPostMapper.selectGroupPostByGroupPostCode(groupPostCode);
    }

    public List<AccGroupPostDTO> findGroupPostByGroupCode(int groupCode) {
        return accGroupPostMapper.selectGroupPostByGroupCode(groupCode);
    }

    public List<AccGroupPostDTO> findGroupPostByMemberCode(int memberCode) {
        return accGroupPostMapper.selectGroupPostByMemberCode(memberCode);
    }
}
