package com.iiiiii.accountbook.community.query.controller;

import com.iiiiii.accountbook.community.query.dto.CommunityPostDTO;
import com.iiiiii.accountbook.community.query.service.CommunityPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityController {

    private final CommunityPostService communityPostService;

    public CommunityController(CommunityPostService communityPostService) {
        this.communityPostService = communityPostService;
    }

    @GetMapping("/post")
    public ResponseEntity<List<CommunityPostDTO>> findAllCommunityPosts() {

        List<CommunityPostDTO> postList = communityPostService.findAllCommunityPosts();

        return ResponseEntity.status(HttpStatus.OK).body(postList);
    }

    @GetMapping("/post/{postCode}")
    public ResponseEntity<CommunityPostDTO> findOneCommunityPost(@PathVariable int postCode) {

        CommunityPostDTO selectedPost = communityPostService.findOneCommunityPost(postCode);

        return ResponseEntity.status(HttpStatus.OK).body(selectedPost);
    }
}
