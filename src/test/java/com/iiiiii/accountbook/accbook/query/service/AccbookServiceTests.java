package com.iiiiii.accountbook.accbook.query.service;

import com.iiiiii.accountbook.accbook.query.dto.AccbookDTO;
import org.junit.jupiter.api.DisplayName;
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

    private static Stream<Arguments> provideAccbookCodeAndDate() {
        return Stream.of(
                Arguments.of(1, "2024-08")
        );
    }

    @DisplayName("월별 가계부 목록 조회 테스트")
    @ParameterizedTest
    @MethodSource("provideAccbookCodeAndDate")
    public void testFindMonthlyAccbookBy(int memberCode, String findDate) {
        // given

        // when
        List<AccbookDTO> foundAccbooks = accbookService.findMonthlyAccbookBy(memberCode, findDate);

        // then
        int expectedSize = 8;
        assertEquals(expectedSize, foundAccbooks.size());
    }
}