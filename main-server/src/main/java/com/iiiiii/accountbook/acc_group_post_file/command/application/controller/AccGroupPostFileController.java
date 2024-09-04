package com.iiiiii.accountbook.acc_group_post_file.command.application.controller;

import com.iiiiii.accountbook.acc_group_post_file.command.application.service.AccGroupPostFileService;
import com.iiiiii.accountbook.acc_group_post_file.command.domain.aggregate.AccGroupPostFile;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> registAccGroupPostFile(@RequestBody AccGroupPostFile newAccGroupPostFile) {
        accGroupPostFileService.registAccGroupPostFile(newAccGroupPostFile);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyAccGroupPostFile(@RequestBody AccGroupPostFile modifyAccGroupPostFile) {
        accGroupPostFileService.modifyAccGroupPostFile(modifyAccGroupPostFile);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccGroupPostFile(@RequestBody AccGroupPostFile deleteAccGroupPostFile) {
        accGroupPostFileService.deleteAccGroupPostFile(deleteAccGroupPostFile);

        return ResponseEntity
                .noContent()
                .build();
    }
}
