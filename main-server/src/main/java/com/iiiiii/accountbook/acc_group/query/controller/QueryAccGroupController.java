package com.iiiiii.accountbook.acc_group.query.controller;

import com.iiiiii.accountbook.acc_group.query.dto.QueryAccGroupDTO;
import com.iiiiii.accountbook.acc_group.query.service.QueryAccGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/acc-group")
public class QueryAccGroupController {
    private QueryAccGroupService queryAccGroupService;

    @Autowired
    public QueryAccGroupController(QueryAccGroupService queryAccGroupService) {
        this.queryAccGroupService = queryAccGroupService;
    }

    @GetMapping("/")
    public ResponseEntity<List<QueryAccGroupDTO>> findAllAccGroup() {

        List<QueryAccGroupDTO> accGroups = queryAccGroupService.findAllAccGroup();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accGroups);
    }

    @GetMapping("/{code}")
    public ResponseEntity<QueryAccGroupDTO> findOneAccGroup(@PathVariable("code") int code) {

        QueryAccGroupDTO accGroup = queryAccGroupService.findOneAccGroup(code);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accGroup);
    }

    @GetMapping("/codes")
    public ResponseEntity<List<Integer>> findAccGroupCode() {

        List<Integer> accGroupCodes = queryAccGroupService.findAccGroupCodes();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accGroupCodes);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> findAccGroupName() {

        List<String> accGroupNames = queryAccGroupService.findAccGroupNames();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accGroupNames);
    }
}
