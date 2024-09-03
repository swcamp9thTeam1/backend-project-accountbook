package com.iiiiii.accountbook.acc_group.command.application.controller;

import com.iiiiii.accountbook.acc_group.command.application.service.AccGroupService;
import com.iiiiii.accountbook.acc_group.command.domain.aggregate.AccGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("CommandAccGroupController")
@RequestMapping("/acc-group")
public class AccGroupController {
    private AccGroupService accGroupService;

    @Autowired
    public AccGroupController(AccGroupService accGroupService) {
        this.accGroupService = accGroupService;
    }

    @PostMapping("/regist")
    public ResponseEntity<?> registAccGroup(@RequestBody AccGroup newAccGroup) {
        accGroupService.registAccGroup(newAccGroup);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyAccGroup(@RequestBody AccGroup modifyAccGroup) {
        accGroupService.modifyAccGroup(modifyAccGroup);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeAccGroup(@RequestBody AccGroup deleteAccGroup) {
        accGroupService.deleteAccGroup(deleteAccGroup);

        return ResponseEntity
                .noContent()        // 204
                .build();
    }
}
