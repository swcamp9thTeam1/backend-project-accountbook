package com.iiiiii.accountbook.acc_group_post.command.application.controller;

import com.iiiiii.accountbook.acc_group_post.command.application.service.AccGroupPostService;
import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.vo.RegisterAccGroupPostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController("GroupPostControllerCommand")
@RequestMapping("/group-posts")
public class AccGroupPostController {

    private final AccGroupPostService accGroupPostService;

    @Autowired
    public AccGroupPostController(AccGroupPostService accGroupPostService) {
        this.accGroupPostService = accGroupPostService;
    }

    @PostMapping("/groups/{groupCode}")
    public ResponseEntity<?> registerAccGroupPost(
            @PathVariable int groupCode,
            @RequestBody RegisterAccGroupPostVO registerAccGroupPostVO)
            throws Exception {

        int newGroupPostId = accGroupPostService.registerGroupPost(groupCode, registerAccGroupPostVO);

        return ResponseEntity
                .created(URI.create("/group-post/" + newGroupPostId))
                .build();
    }
}
