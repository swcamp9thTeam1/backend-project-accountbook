package com.iiiiii.accbookserver.accbook.query.controller;

import com.iiiiii.accbookserver.accbook.query.dto.AccbookCategoryStatsDTO;
import com.iiiiii.accbookserver.accbook.query.dto.AccbookDTO;
import com.iiiiii.accbookserver.accbook.query.dto.AccbookDetailDTO;
import com.iiiiii.accbookserver.accbook.query.dto.AccbookTop3CategoryDTO;
import com.iiiiii.accbookserver.accbook.query.service.AccbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/com/iiiiii/accbookserver/accbook")
public class AccbookController {

    private final AccbookService accbookService;

    @Autowired
    public AccbookController(AccbookService accbookService) {
        this.accbookService = accbookService;
    }

    @GetMapping("daily")
    List<AccbookDTO> findDailyAccbookBy(
            @RequestParam("memberCode") Integer memberCode,
            @RequestParam("findDate") String findDate) {
        return accbookService.findDailyAccbookBy(memberCode, findDate);
    }

    @GetMapping("weekly")
    List<AccbookDTO> findWeeklyAccbookBy(
            @RequestParam("memberCode") Integer memberCode,
            @RequestParam("findDate") String findDate,
            @RequestParam("weekNo") Integer weekNo) {
        return accbookService.findWeeklyAccbookBy(memberCode, findDate, weekNo);
    }

    @GetMapping("monthly")
    List<AccbookDTO> findMonthlyAccbookBy(
            @RequestParam("memberCode") Integer memberCode,
            @RequestParam("findDate") String findDate) {
        return accbookService.findMonthlyAccbookBy(memberCode, findDate);
    }

    @GetMapping("search/monthly-top3-categories")
    List<AccbookTop3CategoryDTO> findMonthlyTop3CategoriesBy(
            @RequestParam("memberCode") Integer memberCode,
            @RequestParam("findDate") String findDate) {
        return accbookService.findMonthlyTop3CategoriesBy(memberCode, findDate);
    }

    @GetMapping("search/monthly-category-stat")
    List<AccbookCategoryStatsDTO> findMonthlyCategoryStatBy(
            @RequestParam("memberCode") Integer memberCode,
            @RequestParam("findDate") String findDate) {
        return accbookService.findMonthlyCategoryStatBy(memberCode, findDate);
    }

    @GetMapping("search/detail")
    AccbookDetailDTO findAccbookDetailBy(
            @RequestParam("accbookCode") Integer accbookCode) {
        return accbookService.findAccbookDetailBy(accbookCode);
    }
}
