package com.iiiiii.accountbook.acc_group.query.service;

import com.iiiiii.accountbook.acc_group.query.dto.QueryAccGroupDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class QueryAccGroupServiceTests {
    private QueryAccGroupService queryAccGroupService;

    @Autowired
    public void setQueryAccGroupService(QueryAccGroupService queryAccGroupService) {
        this.queryAccGroupService = queryAccGroupService;
    }

    @DisplayName("그룹 목록 조회 확인 테스트")
    @Test
    public void testFindAllAccGroup() {
        List<QueryAccGroupDTO> list = queryAccGroupService.findAllAccGroup();

        assertTrue(!list.isEmpty());
    }

    @DisplayName("그룹 코드 조회 확인 테스트")
    @Test
    public void testFindAccGroupCode() {
        List<Integer> list = queryAccGroupService.findAccGroupCodes();

        assertTrue(!list.isEmpty());
    }

    @DisplayName("그룹 이름 조회 확인 테스트")
    @Test
    public void testFindAccGroupName() {
        List<String> list = queryAccGroupService.findAccGroupNames();

        assertTrue(!list.isEmpty());
    }
}
