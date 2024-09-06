package com.iiiiii.accountbook.acc_group.command.application.service;

import com.iiiiii.accountbook.acc_group.command.domain.aggregate.AccGroup;
import com.iiiiii.accountbook.exception.NotAllowedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
public class AccGroupServiceTests {
    private AccGroupService accGroupService;
    private com.iiiiii.accountbook.acc_group.query.service.AccGroupService queryService;

    @Autowired
    public void AccGroupServiceTests(AccGroupService accGroupService,
                                     com.iiiiii.accountbook.acc_group.query.service.AccGroupService queryService) {
        this.accGroupService = accGroupService;
        this.queryService = queryService;
    }

    @DisplayName("그룹 생성 확인 테스트")
    @Test
    public void testRegistAccGroup() {
        int result = accGroupService.registAccGroup(1, new AccGroup("테스트 그룹", "그룹 생성 테스트!!!")).getCode();
        Assertions.assertTrue(result > 0);
    }

    @DisplayName("그룹 수정 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void testModifyAccGroup(int memberCode) throws NotAllowedException {
        if (memberCode == 1)
            Assertions.assertNotNull(accGroupService.modifyAccGroup(memberCode, new AccGroup(1, "그룹수정테스트", "소개99")));
        else {
            try {
                accGroupService.modifyAccGroup(memberCode, new AccGroup(1, "그룹수정테스트", "소개99"));
            } catch (Exception e) {
                Assertions.assertTrue(true);
            }
        }

    }

    @DisplayName("그룹 삭제 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @Transactional
    public void testDeleteAccGroup(int memberCode) throws NotAllowedException {
        if (memberCode == 1) {
            accGroupService.deleteAccGroup(memberCode, new AccGroup(1, "그룹99", "소개99"));
            Assertions.assertNull(queryService.findOneAccGroup(1));
        } else {
            try {
                accGroupService.modifyAccGroup(memberCode, new AccGroup(1, "그룹수정테스트", "소개99"));
            } catch (Exception e) {
                Assertions.assertTrue(true);
            }
        }
    }
}
