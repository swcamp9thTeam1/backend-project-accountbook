package com.iiiiii.accountbook.store.command.application.controller;

import com.iiiiii.accountbook.store.command.application.service.StoreService;
import com.iiiiii.accountbook.store.command.domain.aggregate.vo.RegisterStoreVO;
import com.iiiiii.accountbook.store.command.domain.aggregate.vo.RequestModifyGoodStoreVO;
import com.iiiiii.accountbook.store.command.domain.aggregate.vo.RequestModifyStoreVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController("StoreControllerCommand")
@RequestMapping("/stores")
@Slf4j
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/good")
    public ResponseEntity<?> registerGoodStore(@RequestParam("file") MultipartFile file) throws Exception {

        storeService.registerGoodStore(file);

        return ResponseEntity
                .created(URI.create("/stores/search?is-good=true"))
                .build();
    }

    @PostMapping("")
    public ResponseEntity<?> registerStore(@RequestBody RegisterStoreVO registerStoreVO) {

        int newStoreCode = storeService.registerStore(registerStoreVO);

        return ResponseEntity
                .created(URI.create("/stores/" + newStoreCode))
                .build();
    }

    @PutMapping("/{storeCode}")
    public ResponseEntity<?> modifyStore(@PathVariable int storeCode, @RequestBody RequestModifyStoreVO requestBody)
            throws Exception {

        storeService.modifyStore(storeCode, requestBody);

        return ResponseEntity
                .noContent()
                .header("Content-Location", "/stores/" + storeCode)
                .build();
    }

    @PutMapping("/good/{storeCode}/change-n")
    public ResponseEntity<?> modifyGoodStoreToN(@PathVariable int storeCode) throws Exception {

        storeService.modifyGoodStoreToN(storeCode);

        return ResponseEntity
                .noContent()
                .header("Content-Location", "/stores/" + storeCode)
                .build();
    }

    @PutMapping("/good/{storeCode}")
    public ResponseEntity<?> modifyGoodStore(
            @PathVariable int storeCode,
            @RequestBody RequestModifyGoodStoreVO requestModifyGoodStoreVO) throws Exception {

        storeService.modifyGoodStore(storeCode, requestModifyGoodStoreVO);

        return ResponseEntity
                .noContent()
                .header("Content-Location", "/stores/" + storeCode)
                .build();
    }

    @DeleteMapping("/{storeCode}")
    public ResponseEntity<?> removeStore(@PathVariable int storeCode) throws Exception {

        storeService.removeStore(storeCode);

        return ResponseEntity
                .noContent()
                .build();
    }
}
