package com.iiiiii.accountbook.acc_category.command.application.controller;

import com.iiiiii.accountbook.acc_category.command.application.service.CommandAccCategoryService;
import com.iiiiii.accountbook.acc_category.command.domain.aggregate.AccCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RestController
@RequestMapping("/acc-category")
public class CommandAccCategoryController {
    private CommandAccCategoryService commandAccCategoryService;

    @Autowired
    public CommandAccCategoryController(CommandAccCategoryService commandAccCategoryService) {
        this.commandAccCategoryService = commandAccCategoryService;
    }

    @PostMapping("/regist")
    public ResponseEntity<?> registAccCategory(@RequestBody AccCategory newAccCategory) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json",
                Charset.forName("UTF-8")));

        commandAccCategoryService.registAccCategory(newAccCategory);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyAccCategory(@RequestBody AccCategory modifyAccCategory) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json",
                Charset.forName("UTF-8")));

        commandAccCategoryService.modifyAccCategory(modifyAccCategory);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccCategory(@RequestBody AccCategory deleteAccCategory) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json",
                Charset.forName("UTF-8")));

        commandAccCategoryService.deleteAccCategory(deleteAccCategory);

        return ResponseEntity
                .noContent()
                .build();
    }
}
