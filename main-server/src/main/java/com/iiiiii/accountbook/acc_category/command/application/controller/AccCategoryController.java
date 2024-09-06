package com.iiiiii.accountbook.acc_category.command.application.controller;

import com.iiiiii.accountbook.acc_category.command.application.service.AccCategoryService;
import com.iiiiii.accountbook.acc_category.command.domain.aggregate.AccCategory;
import com.iiiiii.accountbook.acc_category.command.domain.aggregate.AccCategoryEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController("CommandAccCategoryController")
@RequestMapping("/acc-category")
public class AccCategoryController {
    private AccCategoryService accCategoryService;

    @Autowired
    public AccCategoryController(AccCategoryService accCategoryService) {
        this.accCategoryService = accCategoryService;
    }

    @PostMapping("/regist")
    public ResponseEntity<AccCategoryEntity> registAccCategory(@RequestBody AccCategory newAccCategory) {
        AccCategoryEntity result = accCategoryService.registAccCategory(newAccCategory);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PostMapping("/modify")
    public ResponseEntity<AccCategoryEntity> modifyAccCategory(@RequestBody AccCategory modifyAccCategory) {
        AccCategoryEntity result = accCategoryService.modifyAccCategory(modifyAccCategory);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccCategory(@RequestBody AccCategory deleteAccCategory) {
        accCategoryService.deleteAccCategory(deleteAccCategory);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
