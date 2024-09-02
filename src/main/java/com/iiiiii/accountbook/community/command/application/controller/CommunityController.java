package com.iiiiii.accountbook.community.command.application.controller;

import com.iiiiii.accountbook.community.command.application.service.CommunityService;
import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommunityPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController("CommunityControllerCommand")
@RequestMapping("/community")
public class CommunityController {

    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    /* 게시글 등록 */
    @PostMapping("/post")
    public ResponseEntity<?> registPost(@RequestBody CommunityPostDTO newPost) {
        communityService.registPost(newPost);

        return ResponseEntity.created(URI.create("/community/post/" + newPost.getCode())).build();
    }

    /* 게시글 수정 */
    @PutMapping("/post/{code}")
    public ResponseEntity<?> modifyPost(@RequestBody CommunityPostDTO modifiedPost, @PathVariable Integer code) {
        communityService.modifyPost(code, modifiedPost);

        return ResponseEntity.noContent().build();
    }

    /* 게시글 삭제 */
    @DeleteMapping("/post/{code}")
    public ResponseEntity<?> removePost(@PathVariable Integer code) {
        communityService.removePost(code);

        return ResponseEntity.noContent().build();
    }

    /* 첨부파일 등록 */
    // 게시글에서 이어져야 함


    /* 첨부파일 수정 */
    // 게시글에서 이어져야 함


    /* 첨부파일 삭제 */
    // 게시글에서 이어져야 함


    /* 댓글 등록 */


    /* 댓글 수정 */


    /* 댓글 삭제 */

}
