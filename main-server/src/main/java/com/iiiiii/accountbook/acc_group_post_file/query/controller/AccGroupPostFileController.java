package com.iiiiii.accountbook.acc_group_post_file.query.controller;

import com.iiiiii.accountbook.acc_group_post_file.query.dto.AccGroupPostFileDTO;
import com.iiiiii.accountbook.acc_group_post_file.query.service.AccGroupPostFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("QueryAccGroupPostFileController")
@RequestMapping("/acc-group-post-file")
public class AccGroupPostFileController {
    private AccGroupPostFileService accGroupPostFileService;

    @Autowired
    public AccGroupPostFileController(AccGroupPostFileService accGroupPostFileService) {
        this.accGroupPostFileService = accGroupPostFileService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AccGroupPostFileDTO>> findAllGroupPostFile() {
        List<AccGroupPostFileDTO> groupPostFile = accGroupPostFileService.findAllGroupPostFile();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupPostFile);
    }

    @GetMapping("/{fileCode}")
    public ResponseEntity<AccGroupPostFileDTO> findGroupPostFileByFileCode(@PathVariable int fileCode) {
        AccGroupPostFileDTO groupPostFile = accGroupPostFileService.findGroupPostFileByFileCode(fileCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupPostFile);
    }

    @GetMapping("/posts/{postCode}")
    public ResponseEntity<List<AccGroupPostFileDTO>> findGroupPostFileByPostCode(@PathVariable int postCode) {
        List<AccGroupPostFileDTO> groupPostFile = accGroupPostFileService.findGroupPostFileByPostCode(postCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(groupPostFile);
    }
}
