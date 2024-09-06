package com.iiiiii.accountbook.community.command.application.controller;

import com.iiiiii.accountbook.community.command.application.service.CommunityCommentService;
import com.iiiiii.accountbook.community.command.application.service.CommunityFileService;
import com.iiiiii.accountbook.community.command.application.service.CommunityPostService;
import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommnunityFileDTO;
import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommunityCommentDTO;
import com.iiiiii.accountbook.community.command.domain.aggregate.dto.CommunityPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController("CommunityControllerCommand")
@RequestMapping("/community")
public class CommunityController {

    private final CommunityPostService communityPostService;
    private final CommunityFileService communityFileService;
    private final CommunityCommentService communityCommentService;

    @Autowired
    public CommunityController(CommunityPostService communityPostService,
                               CommunityFileService communityFileService,
                               CommunityCommentService communityCommentService) {
        this.communityPostService = communityPostService;
        this.communityFileService = communityFileService;
        this.communityCommentService = communityCommentService;
    }

    /* 게시글 등록 */
    @PostMapping("/post")
    public ResponseEntity<?> registPost(@RequestBody CommunityPostDTO newPost) {
        int registedCode = communityPostService.registPost(newPost);

        return ResponseEntity.created(URI.create("/community/post/" + registedCode)).build();
    }

    /* 게시글 수정 */
    @PutMapping("/post/{postCode}")
    public ResponseEntity<?> modifyPost(@RequestBody CommunityPostDTO modifiedPost, @PathVariable Integer postCode) {
        communityPostService.modifyPost(postCode, modifiedPost);

        return ResponseEntity.noContent().build();
    }

    /* 게시글 삭제 */
    @DeleteMapping("/post/{postCode}")
    public ResponseEntity<?> removePost(@PathVariable Integer postCode) {
        communityPostService.removePost(postCode);

        return ResponseEntity.noContent().build();
    }

    /* 첨부파일 등록 */
    // 게시글에서 이어져야 함
    @PostMapping("/post/{postCode}")
    public ResponseEntity<?> registFile(@RequestBody CommnunityFileDTO newFile, @PathVariable Integer postCode) {
        int registedCode = communityFileService.registFile(postCode, newFile);

        return ResponseEntity.created(URI.create("/community/post/{postCode}/" + registedCode)).build();
    }

    /* 첨부파일 수정 */
    // 게시글에서 이어져야 함
    @PutMapping("/post/{postCode}/{fileCode}")
    public ResponseEntity<?> modifyFile(@RequestBody CommnunityFileDTO modifiedFile,
                                        @PathVariable Integer postCode,
                                        @PathVariable Integer fileCode) {
        communityFileService.modifyFile(postCode, fileCode, modifiedFile);

        return ResponseEntity.noContent().build();
    }

    /* 첨부파일 삭제 */
    // 게시글에서 이어져야 함
    @DeleteMapping("/post/{postCode}/{fileCode}")
    public ResponseEntity<?> removeFile(@PathVariable Integer postCode, @PathVariable Integer fileCode) {
        communityFileService.removeFile(postCode, fileCode);

        return ResponseEntity.noContent().build();
    }

    /* 댓글 및 대댓글 등록 */
    @PostMapping("/post/{postCode}/comment")
    public ResponseEntity<?> registComment(@RequestBody CommunityCommentDTO newComment,
                                           @PathVariable Integer postCode) {
        int registedCode = communityCommentService.registComment(postCode, newComment);

        return ResponseEntity.created(URI.create("/community/post/{postCode}/comment/" + registedCode)).build();
    }

    /* 댓글 및 대댓글 수정 */
    @PutMapping("/post/{postCode}/comment/{commentCode}")
    public ResponseEntity<?> modifyComment(@RequestBody CommunityCommentDTO modifiedComment,
                                           @PathVariable Integer postCode,
                                           @PathVariable Integer commentCode) {
        communityCommentService.modifyComment(postCode, commentCode, modifiedComment);

        return ResponseEntity.noContent().build();
    }

    /* 댓글 및 대댓글 삭제 */
    @DeleteMapping("/post/{postCode}/comment/{commentCode}")
    public ResponseEntity<?> removeComment(@PathVariable Integer postCode, @PathVariable Integer commentCode) {
        communityCommentService.removeComment(postCode, commentCode);

        return ResponseEntity.noContent().build();
    }
}
