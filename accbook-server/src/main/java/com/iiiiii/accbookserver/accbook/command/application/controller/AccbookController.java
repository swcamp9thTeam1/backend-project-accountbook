package com.iiiiii.accbookserver.accbook.command.application.controller;

import com.iiiiii.accbookserver.accbook.command.application.service.AccCommentService;
import com.iiiiii.accbookserver.accbook.command.application.service.AccbookService;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.dto.AccbookDTO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.dto.CreateAccCommentDTO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.dto.UpdateAccCommentDTO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.entity.AccComment;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.entity.Accbook;
import com.iiiiii.accbookserver.common.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController("AccbookControllerCommand")
@RequestMapping("/accbook")
public class AccbookController {

    private AccbookService accbookService;
    private AccCommentService accCommentService;

    @Autowired
    public AccbookController(AccbookService accbookService, AccCommentService accCommentService) {
        this.accbookService = accbookService;
        this.accCommentService = accCommentService;
    }

    /* 가계부 관련 메소드 */
    @PostMapping("")
    public ResponseEntity<?> registAccbook(@RequestBody AccbookDTO newAccbook) {
        Accbook savedAccbook = accbookService.registAccbook(newAccbook);

        return ResponseEntity
                .created(URI.create("/accbook/search/detail?accbookCode="  + savedAccbook.getAccbookCode()))
                .build();
    }

    @PutMapping("/{accbookCode}")
    public ResponseEntity<?> modifyAccbook(@RequestBody AccbookDTO modifyAccbook, @PathVariable Integer accbookCode) {
        Accbook accbook = accbookService.modifyAccbook(accbookCode, modifyAccbook);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("accbook", accbook);

        return ResponseEntity
                .ok(new ResponseMessage(responseMap));
    }

    @DeleteMapping("/{accbookCode}")
    public ResponseEntity<?> removeAccbook(@PathVariable Integer accbookCode) {
        accbookService.removeAccbook(accbookCode);

        return ResponseEntity
                .noContent().build();
    }

    /* 가계부 댓글 관련 메소드 */
    @PostMapping("{accbookCode}/comment")
    public ResponseEntity<?> registAccComment(@RequestBody CreateAccCommentDTO newAccCommentDTO,
                                              @PathVariable Integer accbookCode) {
        AccComment savedAccComment = accCommentService.registAccbookComment(accbookCode, newAccCommentDTO);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("accComment", savedAccComment);
        return ResponseEntity
                .ok(new ResponseMessage(responseMap));

    }

    @PutMapping("{accbookCode}/comment/{accCommentCode}")
    public ResponseEntity<?> modifyAccComment(@RequestBody UpdateAccCommentDTO updateAccCommentDTO,
                                              @PathVariable Integer accbookCode,
                                              @PathVariable Integer accCommentCode) {
        AccComment accComment = accCommentService.modifyAccComment(accCommentCode, updateAccCommentDTO);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("accComment", accComment);

        return ResponseEntity
                .ok(new ResponseMessage(responseMap));
    }

    @DeleteMapping("{accbookCode}/comment/{accCommentCode}")
    public ResponseEntity<?> removeAccComment(@PathVariable Integer accbookCode,
                                              @PathVariable Integer accCommentCode) {
        accCommentService.removeAccComment(accCommentCode);

        return ResponseEntity
                .noContent().build();
    }
}