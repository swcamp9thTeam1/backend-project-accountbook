package com.iiiiii.accountbook.community.query.controller;

import com.iiiiii.accountbook.community.query.dto.CommunityCommentDTO;
import com.iiiiii.accountbook.community.query.dto.CommunityFileDTO;
import com.iiiiii.accountbook.community.query.dto.CommunityPostDTO;
import com.iiiiii.accountbook.community.query.dto.CommunityPostScrapDTO;
import com.iiiiii.accountbook.community.query.service.CommunityCommentService;
import com.iiiiii.accountbook.community.query.service.CommunityFileService;
import com.iiiiii.accountbook.community.query.service.CommunityPostScrapService;
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
    private final CommunityFileService communityFileService;
    private final CommunityCommentService communityCommentService;
    private final CommunityPostScrapService communityPostScrapService;

    public CommunityController(CommunityPostService communityPostService,
                               CommunityFileService communityFileService,
                               CommunityCommentService communityCommentService,
                               CommunityPostScrapService communityPostScrapService) {
        this.communityPostService = communityPostService;
        this.communityFileService = communityFileService;
        this.communityCommentService = communityCommentService;
        this.communityPostScrapService = communityPostScrapService;
    }

    /* 게시글 목록 조회 */
    @GetMapping("/post")
    public ResponseEntity<List<CommunityPostDTO>> findAllCommunityPosts() {
        List<CommunityPostDTO> postList = communityPostService.findAllCommunityPosts();

        return ResponseEntity.status(HttpStatus.OK).body(postList);
    }

    /* 게시글 상세 조회 */
    @GetMapping("/post/{postCode}")
    public ResponseEntity<CommunityPostDTO> findOneCommunityPost(@PathVariable int postCode) {
        CommunityPostDTO selectedPost = communityPostService.findOneCommunityPost(postCode);

        return ResponseEntity.status(HttpStatus.OK).body(selectedPost);
    }

    /* 회원의 게시글 목록 조회 */
    @GetMapping("/post/{memberCode}")
    public ResponseEntity<List<CommunityPostDTO>> findCommunityPostsOfMember(@PathVariable int memberCode) {
        List<CommunityPostDTO> myPostList = communityPostService.findCommunityPostsOfMember(memberCode);

        return ResponseEntity.status(HttpStatus.OK).body(myPostList);
    }

    /* 게시글 첨부파일 조회 */
    @GetMapping("/post/{postCode}/files")
    public ResponseEntity<List<CommunityFileDTO>> findFilesOfCommunityPost(@PathVariable int postCode) {
        List<CommunityFileDTO> fileListOfOnePost = communityFileService.findFilesOfCommunityPost(postCode);

        return ResponseEntity.status(HttpStatus.OK).body(fileListOfOnePost);
    }

    /* 게시글 댓글 조회 */
    @GetMapping("/post/{postCode}/comment")
    public ResponseEntity<List<CommunityCommentDTO>> findCommentsOfCommunityPost(@PathVariable int postCode) {
        List<CommunityCommentDTO> commentListOfOnePost = communityCommentService.findCommentsOfCommunityPost(postCode);

        return ResponseEntity.status(HttpStatus.OK).body(commentListOfOnePost);
    }

    /* 회원의 스크랩 게시글 목록 조회 */
    @GetMapping("/scrap/{memberCode}")
    public ResponseEntity<List<CommunityPostScrapDTO>> findPostScrapOfMember(@PathVariable int memberCode) {
        List<CommunityPostScrapDTO> postScrapList = communityPostScrapService.findPostScrapOfMember(memberCode);

        return ResponseEntity.status(HttpStatus.OK).body(postScrapList);
    }
}
