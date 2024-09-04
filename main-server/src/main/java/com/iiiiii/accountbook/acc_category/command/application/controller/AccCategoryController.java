package com.iiiiii.accountbook.acc_category.command.application.controller;

import com.iiiiii.accountbook.acc_category.command.application.service.AccCategoryService;
import com.iiiiii.accountbook.acc_category.command.domain.aggregate.AccCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> registAccCategory(@RequestBody AccCategory newAccCategory) {

        int registedCode = accCategoryService.registAccCategory(newAccCategory);

        return ResponseEntity.created(URI.create("/acc-category/regist/" + registedCode)).build();
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyAccCategory(@RequestBody AccCategory modifyAccCategory) {

        accCategoryService.modifyAccCategory(modifyAccCategory);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccCategory(@RequestBody AccCategory deleteAccCategory) {

        accCategoryService.deleteAccCategory(deleteAccCategory);

        return ResponseEntity
                .noContent()
                .build();
    }
}
