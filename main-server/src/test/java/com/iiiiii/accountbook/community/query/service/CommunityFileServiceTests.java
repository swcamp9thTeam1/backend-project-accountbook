package com.iiiiii.accountbook.community.query.service;

import com.iiiiii.accountbook.community.query.dto.CommunityFileDTO;
import com.iiiiii.accountbook.community.query.repository.CommunityFileMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CommunityFileServiceTests {

    @Mock
    private CommunityFileMapper communityFileMapper;

    @InjectMocks
    private CommunityFileService communityFileService;

    private static final List<CommunityFileDTO> files = new ArrayList<>();

    @BeforeAll
    public static void setUp() {
        files.add(new CommunityFileDTO(1, "filename1.jpg", "src/path/imagefiles", 1));
        files.add(new CommunityFileDTO(2, "filename2.jpg", "src/path/imagefiles", 2));
        files.add(new CommunityFileDTO(3, "filename3.jpg", "src/path/imagefiles", 2));
    }

    @DisplayName("커뮤니티 게시글의 파일 목록 조회 테스트")
    @Test
    public void selectAllCommunityPosts() {

        // given
        int postCode = 2;
        given(communityFileService.findFilesOfCommunityPost(2)).willReturn(
                files.stream().filter(file -> file.getCommunityPostCode() == postCode)
                              .collect(Collectors.toList()));

        // when
        List<CommunityFileDTO> foundFiles = communityFileService.findFilesOfCommunityPost(postCode);

        // then
        assertThat(foundFiles.size()).isEqualTo(2);
    }
}
