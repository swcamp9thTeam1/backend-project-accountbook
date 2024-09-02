package com.iiiiii.accountbook.acc_group.query.service;

import com.iiiiii.accountbook.acc_group.query.dto.QueryAccGroupDTO;
import com.iiiiii.accountbook.acc_group.query.repository.QueryAccGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryAccGroupService {
    private QueryAccGroupMapper queryAccGroupMapper;

    @Autowired
    public QueryAccGroupService(QueryAccGroupMapper queryAccGroupMapper) {
        this.queryAccGroupMapper = queryAccGroupMapper;
    }

    public List<QueryAccGroupDTO> findAllAccGroup() {
        return queryAccGroupMapper.selectAllAccGroups();
    }

    public QueryAccGroupDTO findOneAccGroup(int code) {
        return queryAccGroupMapper.selectOneAccGroup(code);
    }

    public List<Integer> findAccGroupCodes() {
        return queryAccGroupMapper.selectAllAccGroupCodes();
    }

    public List<String> findAccGroupNames() {
        return queryAccGroupMapper.selectAllAccGroupNames();
    }
}
