package com.iiiiii.accountbook.acc_group_post_file.query.service;

import com.iiiiii.accountbook.acc_group_post_file.query.dto.AccGroupPostFileDTO;
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
public class AccGroupPostFileTests {
    private AccGroupPostFileService accGroupPostFileService;

    @Autowired
    public void setAccGroupPostFileService(AccGroupPostFileService accGroupPostFileService) {
        this.accGroupPostFileService = accGroupPostFileService;
    }

    @DisplayName("그룹 게시글 파일 목록 조회 확인 테스트")
    @Test
    public void testFindAllGroupPostFile() {
        List<AccGroupPostFileDTO> list = accGroupPostFileService.findAllGroupPostFile();
        assertNotNull(list);
    }

    @DisplayName("파일 코드로 그룹 게시글 파일 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5})
    public void testFindOneGroupPostFile(int fileCode) {
        AccGroupPostFileDTO list = accGroupPostFileService.findGroupPostFileByFileCode(fileCode);
        assertNotNull(list);
    }

    @DisplayName("그룹 게시글 코드로 그룹 게시글 파일 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {4, 2, 3})
    public void testFindGroupPostFileByPostCode(int postCode) {
        List<AccGroupPostFileDTO> list = accGroupPostFileService.findGroupPostFileByPostCode(postCode);
        assertNotNull(list);
    }
}
