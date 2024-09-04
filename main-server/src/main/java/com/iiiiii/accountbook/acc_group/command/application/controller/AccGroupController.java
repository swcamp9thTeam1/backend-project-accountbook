package com.iiiiii.accountbook.acc_group.command.application.controller;

import com.iiiiii.accountbook.acc_group.command.application.service.AccGroupService;
import com.iiiiii.accountbook.acc_group.command.domain.aggregate.AccGroup;
import com.iiiiii.accountbook.exception.NotAllowedException;
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
    public ResponseEntity<?> registAccGroup(@RequestParam("memberCode") int memberCode,
                                            @RequestBody AccGroup newAccGroup) {
        accGroupService.registAccGroup(memberCode, newAccGroup);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyAccGroup(@RequestParam("memberCode") int memberCode,
                                            @RequestBody AccGroup modifyAccGroup) throws NotAllowedException {
        accGroupService.modifyAccGroup(memberCode, modifyAccGroup);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeAccGroup(@RequestParam("memberCode") int memberCode,
                                            @RequestBody AccGroup deleteAccGroup) throws NotAllowedException {
        accGroupService.deleteAccGroup(memberCode, deleteAccGroup);

        return ResponseEntity
                .noContent()        // 204
                .build();
    }
}
