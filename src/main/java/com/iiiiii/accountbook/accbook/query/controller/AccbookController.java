package com.iiiiii.accountbook.accbook.query.controller;

import com.iiiiii.accountbook.accbook.query.dto.AccbookDTO;
import com.iiiiii.accountbook.accbook.query.dto.AccbookTop3CategoryDTO;
import com.iiiiii.accountbook.accbook.query.service.AccbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accbook")
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
}
