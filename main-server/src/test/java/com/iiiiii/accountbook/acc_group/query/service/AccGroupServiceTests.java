package com.iiiiii.accountbook.acc_group.query.service;

import com.iiiiii.accountbook.acc_group.query.dto.AccGroupDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccGroupServiceTests {
    private AccGroupService accGroupService;

    @Autowired
    public void setQueryAccGroupService(AccGroupService accGroupService) {
        this.accGroupService = accGroupService;
    }

    @DisplayName("그룹 목록 조회 확인 테스트")
    @Test
    public void testFindAllAccGroup() {
        List<AccGroupDTO> list = accGroupService.findAllAccGroup();

        assertTrue(!list.isEmpty());
    }

    @DisplayName("그룹 코드 조회 확인 테스트")
    @Test
    public void testFindAccGroupCode() {
        List<Integer> list = accGroupService.findAccGroupCodes();

        assertTrue(!list.isEmpty());
    }

    @DisplayName("그룹 이름 조회 확인 테스트")
    @Test
    public void testFindAccGroupName() {
        List<String> list = accGroupService.findAccGroupNames();

        assertTrue(!list.isEmpty());
    }
}
