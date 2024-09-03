package com.iiiiii.accountbook.acc_group.query.controller;

import com.iiiiii.accountbook.acc_group.query.dto.AccGroupDTO;
import com.iiiiii.accountbook.acc_group.query.service.AccGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("QueryAccGroupController")
@RequestMapping("/acc-group")
public class AccGroupController {
    private AccGroupService accGroupService;

    @Autowired
    public AccGroupController(AccGroupService accGroupService) {
        this.accGroupService = accGroupService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AccGroupDTO>> findAllAccGroup() {

        List<AccGroupDTO> accGroups = accGroupService.findAllAccGroup();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accGroups);
    }

    @GetMapping("/{code}")
    public ResponseEntity<AccGroupDTO> findOneAccGroup(@PathVariable("code") int code) {

        AccGroupDTO accGroup = accGroupService.findOneAccGroup(code);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accGroup);
    }

    @GetMapping("/codes")
    public ResponseEntity<List<Integer>> findAccGroupCode() {

        List<Integer> accGroupCodes = accGroupService.findAccGroupCodes();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accGroupCodes);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> findAccGroupName() {

        List<String> accGroupNames = accGroupService.findAccGroupNames();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accGroupNames);
    }
}
