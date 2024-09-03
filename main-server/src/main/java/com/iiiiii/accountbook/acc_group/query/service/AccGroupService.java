package com.iiiiii.accountbook.acc_group.query.service;

import com.iiiiii.accountbook.acc_group.query.dto.AccGroupDTO;
import com.iiiiii.accountbook.acc_group.query.repository.AccGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("QueryAccGroupService")
public class AccGroupService {
    private AccGroupMapper accGroupMapper;

    @Autowired
    public AccGroupService(AccGroupMapper accGroupMapper) {
        this.accGroupMapper = accGroupMapper;
    }

    public List<AccGroupDTO> findAllAccGroup() {
        return accGroupMapper.selectAllAccGroups();
    }

    public AccGroupDTO findOneAccGroup(int code) {
        return accGroupMapper.selectOneAccGroup(code);
    }

    public List<Integer> findAccGroupCodes() {
        return accGroupMapper.selectAllAccGroupCodes();
    }

    public List<String> findAccGroupNames() {
        return accGroupMapper.selectAllAccGroupNames();
    }
}
