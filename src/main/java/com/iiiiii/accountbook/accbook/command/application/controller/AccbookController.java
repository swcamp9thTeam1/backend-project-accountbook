package com.iiiiii.accountbook.accbook.command.application.controller;

import com.iiiiii.accountbook.accbook.command.domain.aggregate.dto.AccbookDTO;
import com.iiiiii.accountbook.accbook.command.domain.aggregate.entity.Accbook;
import com.iiiiii.accountbook.accbook.command.application.service.AccbookService;
import com.iiiiii.accountbook.common.ResponseMessage;
import com.iiiiii.accountbook.common.ResponseStatusText;
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

    @Autowired
    public AccbookController(AccbookService accbookService) {
        this.accbookService = accbookService;
    }

    @PostMapping("/regist")
    public ResponseEntity<?> registAccbook(@RequestBody AccbookDTO newAccbook) {
        Accbook savedAccbook = accbookService.registAccbook(newAccbook);

        return ResponseEntity
                .created(URI.create("/accbook/search/detail?accbookCode="  + savedAccbook.getAccbookCode()))
                .build();
    }

    @PutMapping("/modify/{accbookCode}")
    public ResponseEntity<?> modifyAccbook(@RequestBody AccbookDTO modifyAccbook, @PathVariable Integer accbookCode) {
        Accbook accbook = accbookService.modifyAccbook(accbookCode, modifyAccbook);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("accbook", accbook);

        return ResponseEntity
                .ok(new ResponseMessage(ResponseStatusText.OK, responseMap));
    }

}
