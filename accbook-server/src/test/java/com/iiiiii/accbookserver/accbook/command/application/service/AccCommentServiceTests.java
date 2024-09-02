package com.iiiiii.accbookserver.accbook.command.application.service;

import com.iiiiii.accountbook.accbook.command.domain.aggregate.dto.CreateAccCommentDTO;
import com.iiiiii.accountbook.accbook.command.domain.aggregate.dto.UpdateAccCommentDTO;
import com.iiiiii.accountbook.accbook.command.domain.aggregate.entity.AccComment;
import com.iiiiii.accountbook.accbook.command.domain.repository.AccCommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class AccCommentServiceTests {
    @Autowired
    private AccCommentService accCommentService;

    @Autowired
    private AccCommentRepository accCommentRepository;

    private static Stream<Arguments> provideAccComment() {
        return Stream.of(
                Arguments.of( 1, "2024-08-29", "좋아요!", 1, 1),
                Arguments.of( 2, "2024-08-29", "좀더 절약해봐요..", 1, 2)
        );
    }

    @DisplayName("가계부 코멘트 작성 테스트")
    @ParameterizedTest
    @MethodSource("provideAccComment")
    void testRegistAccbookComment(Integer accbookCode, String createdAt, String detail,
                                  Integer parentCode, Integer memberCode) {
        // given
        CreateAccCommentDTO newAccCommentDTO = new CreateAccCommentDTO();
        newAccCommentDTO.setCreatedAt(createdAt);
        newAccCommentDTO.setDetail(detail);
        newAccCommentDTO.setParentCode(parentCode);
        newAccCommentDTO.setMemberCode(memberCode);

        // when
        AccComment actualAccComment = accCommentService.registAccbookComment(accbookCode, newAccCommentDTO);

        // then
        assertNotNull(actualAccComment);    // 코멘트가 생성되었는지 확인
        assertNotNull(accCommentRepository.findById(actualAccComment.getAccCommentCode())); // 코멘트가 DB에 저장되었는지 확인
    }

    @DisplayName("가계부 코멘트 수정 테스트")
    @ParameterizedTest
    @MethodSource("provideAccComment")
    void testModifyAccComment(Integer accbookCode, String createdAt, String detail,
                              Integer parentCode, Integer memberCode) {
        // given
        AccComment initialAccComment = new AccComment();
        initialAccComment.setAccbookCode(accbookCode);
        initialAccComment.setCreatedAt(createdAt);
        initialAccComment.setDetail("합리적인 소비에요!");
        initialAccComment.setParentCode(parentCode);
        initialAccComment.setMemberCode(memberCode);

        accCommentRepository.save(initialAccComment);

        UpdateAccCommentDTO modifyAccCommentDTO = new UpdateAccCommentDTO();
        modifyAccCommentDTO.setDetail(detail);

        // when
        AccComment modifiedAccComment = accCommentService.modifyAccComment(initialAccComment.getAccCommentCode(), modifyAccCommentDTO);

        // then
        assertEquals(modifyAccCommentDTO.getDetail(), modifiedAccComment.getDetail());
    }

    @DisplayName("가계부 코멘트 삭제 테스트")
    @ParameterizedTest
    @MethodSource("provideAccComment")
    void testRemoveAccComment(Integer accbookCode, String createdAt, String detail,
                              Integer parentCode, Integer memberCode) {
        // given
        AccComment initialAccComment = new AccComment();
        initialAccComment.setAccbookCode(accbookCode);
        initialAccComment.setCreatedAt(createdAt);
        initialAccComment.setDetail(detail);
        initialAccComment.setParentCode(parentCode);
        initialAccComment.setMemberCode(memberCode);

        accCommentRepository.save(initialAccComment);

        int removeAccCommentCode = initialAccComment.getAccCommentCode();
        assertTrue(accCommentRepository.existsById(removeAccCommentCode)); // 테스트하려는 코멘트가 실제 DB에 저장되었는지 확인

        // when
        accCommentService.removeAccComment(removeAccCommentCode);

        // then
        assertFalse(accCommentRepository.findById(removeAccCommentCode).isPresent());
    }
}