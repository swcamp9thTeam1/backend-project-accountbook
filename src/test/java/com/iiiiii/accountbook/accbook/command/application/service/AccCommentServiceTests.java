package com.iiiiii.accountbook.accbook.command.application.service;

import com.iiiiii.accountbook.accbook.command.domain.aggregate.dto.AccCommentDTO;
import com.iiiiii.accountbook.accbook.command.domain.aggregate.entity.AccComment;
import com.iiiiii.accountbook.accbook.command.domain.repository.AccCommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccCommentServiceTests {
    @Autowired
    private AccCommentService accCommentService;

    @Autowired
    private AccCommentRepository accCommentRepository;

    private static Stream<Arguments> provideAccComment() {
        return Stream.of(
                Arguments.of( "2024-08-29", "Test Comment", 1, 1, 1),
                Arguments.of( "2024-08-29", "Test Comment2", 1, 1, 2)
        );
    }

    @DisplayName("가계부 코멘트 작성 테스트")
    @ParameterizedTest
    @MethodSource("provideAccComment")
    void testRegistAccbookComment(String createdAt, String detail,
                                  Integer parentCode, Integer accbookCode, Integer memberCode) {
        // given
        AccCommentDTO newAccCommentDTO = new AccCommentDTO();
        newAccCommentDTO.setCreatedAt(createdAt);
        newAccCommentDTO.setDetail(detail);
        newAccCommentDTO.setParentCode(parentCode);
        newAccCommentDTO.setAccbookCode(accbookCode);
        newAccCommentDTO.setMemberCode(memberCode);

        // given
        AccComment actualAccComment = accCommentService.registAccbookComment(newAccCommentDTO);

        // then
        assertNotNull(actualAccComment);    // 코멘트가 생성되었는지 확인
        assertNotNull(accCommentRepository.findById(actualAccComment.getAccCommentCode())); // 코멘트가 DB에 저장되었는지 확인
    }
}