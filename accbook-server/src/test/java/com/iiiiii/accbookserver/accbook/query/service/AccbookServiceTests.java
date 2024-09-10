package com.iiiiii.accbookserver.accbook.query.service;

import com.iiiiii.accbookserver.accbook.query.dto.*;
import com.iiiiii.accbookserver.accbook.query.repository.AccbookMapper;
import com.iiiiii.accbookserver.common.InOrOut;
import com.iiiiii.accbookserver.common.InOrOutOrTransfer;
import com.iiiiii.accbookserver.common.YesOrNo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@SpringBootTest
//@Transactional
@ExtendWith(MockitoExtension.class)
class AccbookServiceTests {

    @Mock
    private AccbookMapper accbookMapper;

    @InjectMocks
    private AccbookService accbookService;

    @BeforeEach
    public void setup() {
        // Mock 객체 초기화
        MockitoAnnotations.openMocks(this);
    }

    private static Stream<Arguments> provideMemberCodeAndDate() {
        return Stream.of(
                Arguments.of(1, "2024-08-01"),
                Arguments.of(1, "2024-08-02")
        );
    }

    private static Stream<Arguments> provideMemberCodeAndYearMonth() {
        return Stream.of(
                Arguments.of(1, "2024-08"),
                Arguments.of(1, "2024-07")
        );
    }

    private static Stream<Arguments> provideMemberCodeAndDateAndWeekNo() {
        return Stream.of(
                Arguments.of(1, "2024-08", 2),
                Arguments.of(1, "2024-07", 2)
        );
    }

    private static Stream<Arguments> provideAccbookCode() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2)
        );
    }

    @DisplayName("가계부 내역이 원하는 날짜에 정확히 조회되는지 테스트")
    @ParameterizedTest
    @MethodSource("provideMemberCodeAndDate")
    public void testFindDailyAccbookBy_ValidDate(int memberCode, String findDate) {
        // given

        // when
        List<AccbookDTO> foundAccbooks = accbookService.findDailyAccbookBy(memberCode, findDate);

        // then
        for (AccbookDTO accbook : foundAccbooks) { // 조회한 리스트의 date가 find Date와 같은지 테스트
            assertEquals(findDate, accbook.getCreatedAt().toLocalDate().toString());
        }
    }

    @DisplayName("조회하려는 날짜의 가계부 내역이 없는 경우 테스트")
    @ParameterizedTest
    @MethodSource("provideMemberCodeAndDate")
    public void testFindDailyAccbookBy_NoAccbook(int memberCode, String findDate) {
        // given: 가계부 내역이 없는 상황의 Mocking
        when(accbookService.findDailyAccbookBy(memberCode, findDate))
                .thenReturn(Collections.emptyList()); // 빈 리스트를 반환하도록 설정

        // when
        List<AccbookDTO> foundAccbooks = accbookService.findDailyAccbookBy(memberCode, findDate);

        // then
        assertTrue(foundAccbooks.isEmpty());
    }

    @DisplayName("원하는 주의 가계부 내역이 정확히 조회되는지 테스트")
    @ParameterizedTest
    @MethodSource("provideMemberCodeAndDateAndWeekNo")
    public void testFindWeeklyAccbookBy(int memberCode, String findDate, Integer weekNo) {
        // given
        List<AccbookDTO> mockAccbooks = new ArrayList<>();
        mockAccbooks.add(new AccbookDTO(1, LocalDateTime.of(2024, 8, 10, 0, 0), 30000L, YesOrNo.N, "교보문고", memberCode, 1, 1, 1, InOrOutOrTransfer.I));
        mockAccbooks.add(new AccbookDTO(2, LocalDateTime.of(2024, 8, 11, 0, 0), 12000L, YesOrNo.N, "마라탕", memberCode, 2, null, 1, InOrOutOrTransfer.I));
        mockAccbooks.add(new AccbookDTO(3, LocalDateTime.of(2024, 7, 12, 0, 0), 4000L, YesOrNo.N, "카페", memberCode, 2, null, 1, InOrOutOrTransfer.I));
        mockAccbooks.add(new AccbookDTO(4, LocalDateTime.of(2024, 7, 13, 0, 0), 45000L, YesOrNo.N, "올리브영", memberCode, 1, null, 1, InOrOutOrTransfer.I));

        // findDate를 주 번호로 변환
        LocalDate findDateAsLocalDate = LocalDate.parse(findDate + "-01");
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 4);

        // mock 데이터에서 특정 주에 해당하는 데이터만 필터링
        List<AccbookDTO> filteredAccbooks = mockAccbooks.stream()
                .filter(accbook -> {
                    LocalDate createdAt = accbook.getCreatedAt().toLocalDate();
                    int filteredWeekNo = createdAt.get(weekFields.weekOfMonth());
                    return filteredWeekNo == weekNo
                            && createdAt.getYear() == findDateAsLocalDate.getYear()
                            && createdAt.getMonth() == findDateAsLocalDate.getMonth();
                })
                .collect(Collectors.toList());

        when(accbookService.findWeeklyAccbookBy(memberCode, findDate, weekNo)).thenReturn(filteredAccbooks);     // expected

        // when
        List<AccbookDTO> foundAccbooks = accbookService.findWeeklyAccbookBy(memberCode, findDate, weekNo);      // actual

        // then
        // 반환된 데이터의 주 번호가 예상 주 번호와 일치하는지 테스트
        for (AccbookDTO accbook : foundAccbooks) {
            LocalDate createdAt = accbook.getCreatedAt().toLocalDate();
            int actualWeekNo = createdAt.get(weekFields.weekOfMonth());

            assertEquals(weekNo, actualWeekNo);
        }
    }

    @DisplayName("월별 가계부 목록 조회 테스트")
    @ParameterizedTest
    @MethodSource("provideMemberCodeAndYearMonth")
    public void testFindMonthlyAccbookBy(int memberCode, String findDate) {
        // given
        List<AccbookDTO> mockAccbooks = new ArrayList<>();
        mockAccbooks.add(new AccbookDTO(1, LocalDateTime.of(2024, 8, 1, 0, 0), 30000L, YesOrNo.N, "교보문고", memberCode, 1, 1, 1, InOrOutOrTransfer.I));
        mockAccbooks.add(new AccbookDTO(2, LocalDateTime.of(2024, 8, 2, 0, 0), 12000L, YesOrNo.N, "마라탕", memberCode, 2, null, 1, InOrOutOrTransfer.I));
        mockAccbooks.add(new AccbookDTO(3, LocalDateTime.of(2024, 8, 2, 0, 0), 4000L, YesOrNo.N, "카페", memberCode, 2, null, 1, InOrOutOrTransfer.I));
        mockAccbooks.add(new AccbookDTO(4, LocalDateTime.of(2024, 7, 2, 0, 0), 45000L, YesOrNo.N, "올리브영", memberCode, 1, null, 1, InOrOutOrTransfer.I));

        // mockAccbooks 리스트에서 지정된 findDate와 같은 가계부 내역만 리턴 (expected)
        when(accbookService.findMonthlyAccbookBy(memberCode, findDate)).thenReturn(mockAccbooks.stream()
                .filter(accbook -> YearMonth.from(accbook.getCreatedAt().toLocalDate()).equals(YearMonth.parse(findDate, DateTimeFormatter.ofPattern("yyyy-MM"))))
                .collect(Collectors.toList()));

        // when
        List<AccbookDTO> foundAccbooks = accbookService.findMonthlyAccbookBy(memberCode, findDate); // 실제 동작(actual)

        // then
        YearMonth findDateAsYearMonth = YearMonth.parse(findDate, DateTimeFormatter.ofPattern("yyyy-MM"));

        for (AccbookDTO accbook : foundAccbooks) {
            LocalDate createdAt = accbook.getCreatedAt().toLocalDate();
            YearMonth createdYearMonth = YearMonth.from(createdAt);

            assertEquals(createdYearMonth, findDateAsYearMonth); // expected, actual
        }

        assertTrue(foundAccbooks.stream().allMatch(accbook
                -> YearMonth.from(accbook.getCreatedAt().toLocalDate()).equals(findDateAsYearMonth)));
    }

    @DisplayName("월별 Top3 지출 카테고리 조회 테스트")
    @ParameterizedTest
    @MethodSource("provideMemberCodeAndYearMonth")
    public void testFindMonthlyTop3CategoriesBy(int memberCode, String findDate) {
        // given
        List<AccbookTop3CategoryDTO> mockAccbooks = new ArrayList<>();
        mockAccbooks.add(new AccbookTop3CategoryDTO(memberCode, findDate, "식비", 300000L, 1));
        mockAccbooks.add(new AccbookTop3CategoryDTO(memberCode, findDate, "생활비", 250000L, 2));
        mockAccbooks.add(new AccbookTop3CategoryDTO(memberCode, findDate, "의류", 200000L, 3));

        when(accbookService.findMonthlyTop3CategoriesBy(memberCode, findDate)).thenReturn(mockAccbooks.stream()         // expected
                .filter((accbook -> accbook.getMonthYear().equals(findDate)))
                .collect(Collectors.toList()));

        // when
        List<AccbookTop3CategoryDTO> foundAccbooks = accbookService.findMonthlyTop3CategoriesBy(memberCode, findDate);   // actual

        // then
        assertEquals(3, foundAccbooks.size());
        for (AccbookTop3CategoryDTO accbook : foundAccbooks) {
            assertEquals(findDate, accbook.getMonthYear()); // expected, actual
        }
    }

    @DisplayName("월별 전체 카테고리 수입, 지출 통계 조회 테스트")
    @ParameterizedTest
    @MethodSource("provideMemberCodeAndDate")
    public void testFindMonthlyCategoryStatBy(int memberCode, String findDate) {
        // given
        List<AccbookCategoryStatsDTO> mockAccbooks = new ArrayList<>();
        mockAccbooks.add(new AccbookCategoryStatsDTO("식비", InOrOutOrTransfer.O, 300000L));
        mockAccbooks.add(new AccbookCategoryStatsDTO("월급", InOrOutOrTransfer.I, 5000000L));
        mockAccbooks.add(new AccbookCategoryStatsDTO("생활비", InOrOutOrTransfer.O, 400000L));

        when(accbookService.findMonthlyCategoryStatBy(memberCode, findDate)).thenReturn(mockAccbooks);

        // when
        List<AccbookCategoryStatsDTO> foundAccbooks = accbookService.findMonthlyCategoryStatBy(memberCode, findDate);   // actual

        // then
        assertTrue(foundAccbooks.stream().anyMatch(category -> category.getCategoryName().equals("식비") && category.getFinanceType().equals(InOrOutOrTransfer.O) &&category.getTotalSpent().equals(300000L)));
        assertTrue(foundAccbooks.stream().anyMatch(category -> category.getCategoryName().equals("월급") && category.getFinanceType().equals(InOrOutOrTransfer.I) &&category.getTotalSpent().equals(5000000L)));
        assertTrue(foundAccbooks.stream().anyMatch(category -> category.getCategoryName().equals("생활비") && category.getFinanceType().equals(InOrOutOrTransfer.O) &&category.getTotalSpent().equals(400000L)));
    }

    @DisplayName("가계부 코멘트 조회 테스트")
    @ParameterizedTest
    @MethodSource("provideAccbookCode")
    public void testfindAccbookDetailBy(int accbookCode) {
        // given
        List<AccCommentDTO> comments = new ArrayList<>();
        comments.add(new AccCommentDTO("굿굿!", "2024-09-02", 1, null, 2));
        comments.add(new AccCommentDTO("좋아요!", "2024-09-02", 2, null, 3));

        AccbookDetailDTO mockAccbook = new AccbookDetailDTO(accbookCode, "2024-09-01", 10000L, YesOrNo.N,
                "편의점", "생활비", "마트", "신용카드", InOrOutOrTransfer.I, comments);

        when(accbookService.findAccbookDetailBy(accbookCode)).thenReturn(mockAccbook);

        // when
        AccbookDetailDTO foundAccbook = accbookService.findAccbookDetailBy(accbookCode);

        // then
        assertEquals(foundAccbook, mockAccbook);
    }
}