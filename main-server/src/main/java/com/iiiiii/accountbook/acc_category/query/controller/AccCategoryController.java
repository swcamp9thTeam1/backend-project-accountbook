package com.iiiiii.accountbook.acc_category.query.controller;

import com.iiiiii.accountbook.acc_category.query.dto.AccCategoryDTO;
import com.iiiiii.accountbook.acc_category.query.service.AccCategoryService;
import com.iiiiii.accountbook.common.InOrOut;
import com.iiiiii.accountbook.common.YesOrNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("QueryAccCategoryController")
@RequestMapping("/acc-category")
public class AccCategoryController {
    private AccCategoryService accCategoryService;

    @Autowired
    public AccCategoryController(AccCategoryService accCategoryService) {
        this.accCategoryService = accCategoryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AccCategoryDTO>> findAllAccCategory() {

        List<AccCategoryDTO> accCategories = accCategoryService.findAllAccCategory();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }

    @GetMapping("/{categoryCode}")
    public ResponseEntity<AccCategoryDTO> findOneAccCategory(@PathVariable("categoryCode") int categoryCode) {

        AccCategoryDTO accCategory = accCategoryService.findOneAccCategory(categoryCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategory);
    }

    @GetMapping("/members/{memberCode}")
    public ResponseEntity<List<AccCategoryDTO>> findAccCategoryByMemberCode(@PathVariable("memberCode") int memberCode) {

        List<AccCategoryDTO> accCategories = accCategoryService.findAccCategoryByMemberCode(memberCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }

    @GetMapping("/codes/{memberCode}")
    public ResponseEntity<List<Integer>> findAccCategoryCode(@PathVariable("memberCode") int memberCode) {

        List<Integer> accCategories = accCategoryService.findAllAccCategoryCode(memberCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }

    @GetMapping("/names/{memberCode}")
    public ResponseEntity<List<String>> findAccCategoryName(@PathVariable("memberCode") int memberCode) {

        List<String> accCategories = accCategoryService.findAllAccCategoryName(memberCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }

    @GetMapping("/in-or-out")
    public ResponseEntity<List<AccCategoryDTO>> findAccCategoryByIO(@RequestParam("memberCode") int memberCode,
                                                                    @RequestParam("financeType") InOrOut financeType) {

        List<AccCategoryDTO> accCategories = accCategoryService.findAccCategoryByIO(memberCode, financeType);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }

    @GetMapping("/is-deleted")
    public ResponseEntity<List<AccCategoryDTO>> findAccCategoryByFinanceType(@RequestParam("memberCode") int memberCode,
                                                                             @RequestParam("isDeleted") YesOrNo isDeleted) {

        List<AccCategoryDTO> accCategories = accCategoryService.findAccCategoryByIsDeleted(memberCode, isDeleted);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }

    @GetMapping("/visibility")
    public ResponseEntity<List<AccCategoryDTO>> findAccCategoryByVisibility(@RequestParam("memberCode") int memberCode,
                                                                            @RequestParam("visibility") YesOrNo visibility) {

        List<AccCategoryDTO> accCategories = accCategoryService.findAccCategoryByVisibility(memberCode, visibility);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accCategories);
    }
}
