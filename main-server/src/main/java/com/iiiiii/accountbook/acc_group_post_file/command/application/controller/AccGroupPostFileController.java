package com.iiiiii.accountbook.acc_group_post_file.command.application.controller;

import com.iiiiii.accountbook.acc_group_post_file.command.application.service.AccGroupPostFileService;
import com.iiiiii.accountbook.acc_group_post_file.command.domain.aggregate.AccGroupPostFile;
import com.iiiiii.accountbook.acc_group_post_file.command.domain.aggregate.AccGroupPostFileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("CommandAccGroupPostFileController")
@RequestMapping("/acc-group-post-file")
public class AccGroupPostFileController {
    private AccGroupPostFileService accGroupPostFileService;

    @Autowired
    public AccGroupPostFileController(AccGroupPostFileService accGroupPostFileService) {
        this.accGroupPostFileService = accGroupPostFileService;
    }

    @PostMapping("/regist")
    public ResponseEntity<AccGroupPostFileEntity> registAccGroupPostFile(@RequestBody AccGroupPostFile newAccGroupPostFile) {
        AccGroupPostFileEntity result = accGroupPostFileService.registAccGroupPostFile(newAccGroupPostFile);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PostMapping("/modify")
    public ResponseEntity<AccGroupPostFileEntity> modifyAccGroupPostFile(@RequestBody AccGroupPostFile modifyAccGroupPostFile) {
        AccGroupPostFileEntity result = accGroupPostFileService.modifyAccGroupPostFile(modifyAccGroupPostFile);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccGroupPostFile(@RequestBody AccGroupPostFile deleteAccGroupPostFile) {
        accGroupPostFileService.deleteAccGroupPostFile(deleteAccGroupPostFile);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
