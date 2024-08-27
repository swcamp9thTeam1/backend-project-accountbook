package com.iiiiii.accountbook.store.command.application.controller;

import com.iiiiii.accountbook.exception.NotValidRequestException;
import com.iiiiii.accountbook.store.command.application.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController("StoreControllerCommand")
@RequestMapping("/stores")
@Slf4j
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/file")
    public ResponseEntity<?> registerStore(@RequestParam("file") MultipartFile file)
            throws Exception {

        // 엑셀 파일인지 확인
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!"xls".equals(extension) && !"xlsx".equals(extension)) {
            throw new NotValidRequestException("엑셀 파일만 업로드 해주세요.");
        }

        storeService.registerStore(file, extension);

        return ResponseEntity
                .created(URI.create("/stores/search?is-good=true"))
                .build();
    }
}
