package com.iiiiii.accountbook.acc_category.query.controller;

import com.iiiiii.accountbook.acc_category.query.dto.QueryAccCategoryDTO;
import com.iiiiii.accountbook.acc_category.query.service.QueryAccCategoryService;
import com.iiiiii.accountbook.common.InOrOut;
import com.iiiiii.accountbook.common.ResponseMessage;
import com.iiiiii.accountbook.common.ResponseStatusText;
import com.iiiiii.accountbook.common.YesOrNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/acc-category")
public class QueryAccCategoryController {
    private QueryAccCategoryService queryAccCategoryService;

    @Autowired
    public QueryAccCategoryController(QueryAccCategoryService queryAccCategoryService) {
        this.queryAccCategoryService = queryAccCategoryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<QueryAccCategoryDTO>> findAllAccCategory() {

        List<QueryAccCategoryDTO> accCategories = queryAccCategoryService.findAllAccCategory();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }

    @GetMapping("/{memberCode}")
    public ResponseEntity<List<QueryAccCategoryDTO>> findAccCategoryByMemberCode(@PathVariable("memberCode") int memberCode) {

        List<QueryAccCategoryDTO> accCategories = queryAccCategoryService.findAccCategoryByMemberCode(memberCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }

    @GetMapping("/codes/{memberCode}")
    public ResponseEntity<List<Integer>> findAccCategoryCode(@PathVariable("memberCode") int memberCode) {

        List<Integer> accCategories = queryAccCategoryService.findAllAccCategoryCode(memberCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }

    @GetMapping("/names/{memberCode}")
    public ResponseEntity<List<String>> findAccCategoryName(@PathVariable("memberCode") int memberCode) {

        List<String> accCategories = queryAccCategoryService.findAllAccCategoryName(memberCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }

    @GetMapping("/in-or-out")
    public ResponseEntity<List<QueryAccCategoryDTO>> findAccCategoryByIO(@RequestParam("memberCode") int memberCode,
                                                               @RequestParam("financeType") InOrOut financeType) {

        List<QueryAccCategoryDTO> accCategories = queryAccCategoryService.findAccCategoryByIO(memberCode, financeType);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }

    @GetMapping("/is-deleted")
    public ResponseEntity<List<QueryAccCategoryDTO>> findAccCategoryByFinanceType(@RequestParam("memberCode") int memberCode,
                                                                        @RequestParam("isDeleted") YesOrNo isDeleted) {

        List<QueryAccCategoryDTO> accCategories = queryAccCategoryService.findAccCategoryByIsDeleted(memberCode, isDeleted);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }

    @GetMapping("/visibility")
    public ResponseEntity<List<QueryAccCategoryDTO>> findAccCategoryByVisibility(@RequestParam("memberCode") int memberCode,
                                                                        @RequestParam("visibility") YesOrNo visibility) {

        List<QueryAccCategoryDTO> accCategories = queryAccCategoryService.findAccCategoryByVisibility(memberCode, visibility);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }
}
