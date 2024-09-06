package com.iiiiii.accountbook.acc_group_post.query.service;

import com.iiiiii.accountbook.acc_group_post.query.dto.AccGroupPostDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccGroupPostServiceTests {
    private AccGroupPostService accGroupPostService;

    @Autowired
    public void setAccGroupPostService(AccGroupPostService accGroupPostService) {
        this.accGroupPostService = accGroupPostService;
    }

    @DisplayName("그룹 게시글 목록 조회 확인 테스트")
    @Test
    public void testFindAllGroupPost() {
        List<AccGroupPostDTO> list = accGroupPostService.findAllGroupPost();
        assertNotNull(list);
    }

    @DisplayName("그룹 게시글 코드로 그룹 게시글 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5})
    public void testFindGroupPostByGroupPostCode(int groupPostCode) {
        AccGroupPostDTO list = accGroupPostService.findGroupPostByGroupPostCode(groupPostCode);
        assertNotNull(list);
    }

    @DisplayName("그룹 코드로 그룹 게시글 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 2, 4})
    public void testFindGroupPostByGroupCode(int groupCode) {
        List<AccGroupPostDTO> list = accGroupPostService.findGroupPostByGroupCode(groupCode);
        assertNotNull(list);
    }

    @DisplayName("회원 코드로 그룹 게시글 목록 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {4, 2})
    public void testFindGroupPostByMemberCode(int memberCode) {
        List<AccGroupPostDTO> list = accGroupPostService.findGroupPostByMemberCode(memberCode);
        assertNotNull(list);
    }
}
