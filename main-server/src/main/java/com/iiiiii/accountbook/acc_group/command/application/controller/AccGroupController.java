package com.iiiiii.accountbook.acc_group.command.application.controller;

import com.iiiiii.accountbook.acc_group.command.application.service.AccGroupService;
import com.iiiiii.accountbook.acc_group.command.domain.aggregate.AccGroup;
import com.iiiiii.accountbook.acc_group.command.domain.aggregate.AccGroupEntity;
import com.iiiiii.accountbook.exception.NotAllowedException;
import com.iiiiii.accountbook.exception.NotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<AccGroupEntity> registAccGroup(@RequestParam("memberCode") int memberCode,
                                                         @RequestBody AccGroup newAccGroup) throws NotAvailableException {
        AccGroupEntity result = accGroupService.registAccGroup(memberCode, newAccGroup);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PostMapping("/modify")
    public ResponseEntity<AccGroupEntity> modifyAccGroup(@RequestParam("memberCode") int memberCode,
                                            @RequestBody AccGroup modifyAccGroup) throws NotAllowedException {
        AccGroupEntity result = accGroupService.modifyAccGroup(memberCode, modifyAccGroup);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeAccGroup(@RequestParam("memberCode") int memberCode,
                                            @RequestBody AccGroup deleteAccGroup) throws NotAllowedException {
        accGroupService.deleteAccGroup(memberCode, deleteAccGroup);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
