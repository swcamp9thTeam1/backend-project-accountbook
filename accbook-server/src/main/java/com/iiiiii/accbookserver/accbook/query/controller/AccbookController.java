package com.iiiiii.accbookserver.accbook.query.controller;

import com.iiiiii.accbookserver.accbook.query.dto.AccbookCategoryStatsDTO;
import com.iiiiii.accbookserver.accbook.query.dto.AccbookDTO;
import com.iiiiii.accbookserver.accbook.query.dto.AccbookDetailDTO;
import com.iiiiii.accbookserver.accbook.query.dto.AccbookTop3CategoryDTO;
import com.iiiiii.accbookserver.accbook.query.service.AccbookService;
import com.iiiiii.accbookserver.common.ResponseMessageGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accbook")
public class AccbookController {

    private final AccbookService accbookService;

    @Autowired
    public AccbookController(AccbookService accbookService) {
        this.accbookService = accbookService;
    }

    @GetMapping("daily")
    ResponseEntity<ResponseMessageGeneric<List<AccbookDTO>>> findDailyAccbookBy(
            @RequestParam("memberCode") Integer memberCode,
            @RequestParam("findDate") String findDate) {
        List<AccbookDTO> accbooks = accbookService.findDailyAccbookBy(memberCode, findDate);
        return ResponseEntity.ok(new ResponseMessageGeneric<>(accbooks));

    }

    @GetMapping("weekly")
    ResponseEntity<ResponseMessageGeneric<List<AccbookDTO>>> findWeeklyAccbookBy(
            @RequestParam("memberCode") Integer memberCode,
            @RequestParam("findDate") String findDate,
            @RequestParam("weekNo") Integer weekNo) {
        List<AccbookDTO> accbooks = accbookService.findWeeklyAccbookBy(memberCode, findDate, weekNo);
        return ResponseEntity.ok(new ResponseMessageGeneric<>(accbooks));
    }

    @GetMapping("monthly")
    ResponseEntity<ResponseMessageGeneric<List<AccbookDTO>>> findMonthlyAccbookBy(
            @RequestParam("memberCode") Integer memberCode,
            @RequestParam("findDate") String findDate) {
        List<AccbookDTO> accbooks = accbookService.findMonthlyAccbookBy(memberCode, findDate);
        return ResponseEntity.ok(new ResponseMessageGeneric<>(accbooks));
    }

    @GetMapping("monthly-top3-categories")
    ResponseEntity<ResponseMessageGeneric<List<AccbookTop3CategoryDTO>>> findMonthlyTop3CategoriesBy(
            @RequestParam("memberCode") Integer memberCode,
            @RequestParam("findDate") String findDate) {
        List<AccbookTop3CategoryDTO> accbooks = accbookService.findMonthlyTop3CategoriesBy(memberCode, findDate);
        return ResponseEntity.ok(new ResponseMessageGeneric<>(accbooks));

    }

    @GetMapping("monthly-category-stat")
    ResponseEntity<ResponseMessageGeneric<List<AccbookCategoryStatsDTO>>> findMonthlyCategoryStatBy(
            @RequestParam("memberCode") Integer memberCode,
            @RequestParam("findDate") String findDate) {
        List<AccbookCategoryStatsDTO> accbooks = accbookService.findMonthlyCategoryStatBy(memberCode, findDate);
        return ResponseEntity.ok(new ResponseMessageGeneric<>(accbooks));
    }

    @GetMapping("detail/{accbookCode}")
    ResponseEntity<ResponseMessageGeneric<AccbookDetailDTO>> findAccbookDetailBy(
            @PathVariable("accbookCode") Integer accbookCode) {

        AccbookDetailDTO foundAccbook = accbookService.findAccbookDetailBy(accbookCode);
        return ResponseEntity.ok(new ResponseMessageGeneric<>(foundAccbook));
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Accbook health test";
    }
}
