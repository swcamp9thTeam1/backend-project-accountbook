package com.iiiiii.accountbook.acc_group.command.application.controller;

import com.iiiiii.accountbook.acc_group.command.application.service.CommandAccGroupService;
import com.iiiiii.accountbook.acc_group.command.domain.aggregate.CommandAccGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acc-group")
public class CommandAccGroupController {
    private CommandAccGroupService commandAccGroupService;

    @Autowired
    public CommandAccGroupController(CommandAccGroupService commandAccGroupService) {
        this.commandAccGroupService = commandAccGroupService;
    }

    @PostMapping("/regist")
    public ResponseEntity<?> registAccGroup(@RequestBody CommandAccGroup newAccGroup) {
        commandAccGroupService.registAccGroup(newAccGroup);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyAccGroup(@RequestBody CommandAccGroup modifyAccGroup) {
        commandAccGroupService.modifyAccGroup(modifyAccGroup);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeAccGroup(@RequestBody CommandAccGroup deleteAccGroup) {
        commandAccGroupService.deleteAccGroup(deleteAccGroup);

        return ResponseEntity
                .noContent()        // 204
                .build();
    }
}
