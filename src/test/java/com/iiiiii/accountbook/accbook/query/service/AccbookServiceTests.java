package com.iiiiii.accountbook.accbook.query.service;

import com.iiiiii.accountbook.accbook.query.dto.AccbookDTO;
import com.iiiiii.accountbook.accbook.query.dto.AccbookTop3CategoryDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccbookServiceTests {

    @Autowired
    private AccbookService accbookService;

    private static Stream<Arguments> provideMemberCodeAndDate() {
        return Stream.of(
                Arguments.of(1, "2024-08")
        );
    }


    @DisplayName("월별 가계부 목록 조회 테스트")
    @ParameterizedTest
    @MethodSource("provideMemberCodeAndDate")
    public void testFindMonthlyAccbookBy(int memberCode, String findDate) {
        // given

        // when

        // then
        assertDoesNotThrow(() -> accbookService.findMonthlyAccbookBy(1, "2024-07"));
    }
    @DisplayName("월별 Top3 지출 카테고리 조회 테스트")
    @ParameterizedTest
    @MethodSource("provideMemberCodeAndDate")
    public void testFindMonthlyTop3CategoriesBy(int memberCode, String findDate) {
        // given

        // when

        // then
        assertDoesNotThrow(() -> accbookService.findMonthlyTop3CategoriesBy(memberCode, findDate));

    }
}