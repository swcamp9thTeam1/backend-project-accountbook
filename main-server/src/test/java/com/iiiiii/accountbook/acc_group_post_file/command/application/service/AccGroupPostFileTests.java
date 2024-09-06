package com.iiiiii.accountbook.acc_group_post_file.command.application.service;

import com.iiiiii.accountbook.acc_group_post_file.command.domain.aggregate.AccGroupPostFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccGroupPostFileTests {
    private AccGroupPostFileService accGroupPostFileService;
    private com.iiiiii.accountbook.acc_group_post_file.query.service.AccGroupPostFileService queryService;

    @Autowired
    public void setAccGroupPostFileService(AccGroupPostFileService accGroupPostFileService,
                                           com.iiiiii.accountbook.acc_group_post_file.query.service.AccGroupPostFileService queryService) {
        this.accGroupPostFileService = accGroupPostFileService;
        this.queryService = queryService;
    }

    @DisplayName("그룹 게시글 파일 생성 확인 테스트")
    @Test
    public void testRegistAccGroupPostFile() {
        AccGroupPostFile newPostFile = new AccGroupPostFile("테스트 파일", "com/iiiiii/accountbook/acc_group_post_file", 1);
        int result = accGroupPostFileService.registAccGroupPostFile(newPostFile).getCode();
        assertTrue(result > 0);
    }

    @DisplayName("그룹 게시글 파일 수정 확인 테스트")
    @Test
    public void testModifyAccGroupPostFile() {
        AccGroupPostFile modifyPostFile = new AccGroupPostFile(1, "수정 테스트 파일", "com/iiiiii/accbookdb/acc_group_post_file", 3);
        String result = accGroupPostFileService.modifyAccGroupPostFile(modifyPostFile).getName();
        assertTrue("수정 테스트 파일".equals(result));
    }

    @DisplayName("그룹 게시글 파일 삭제 확인 테스트")
    @Test
    public void testDeleteAccGroupPostFile() {
        AccGroupPostFile deletePostFile = new AccGroupPostFile(1, "테스트 파일", "com/iiiiii/accbookdb/acc_group_post_file", 3);
        accGroupPostFileService.deleteAccGroupPostFile(deletePostFile);
        assertNull(queryService.findGroupPostFileByFileCode(1));
    }
}
