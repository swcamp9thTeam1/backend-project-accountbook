package com.iiiiii.accountbook.asset.command.controller;

import com.iiiiii.accountbook.asset.command.dto.AssetDTO;
import com.iiiiii.accountbook.asset.command.service.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/assets")
@Slf4j
public class AssetController {

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetService registAsset) {
        this.assetService = registAsset;
    }

    @GetMapping("/regist")
    public void registAsset() {
    }

    @PostMapping("/regist")
    public ResponseEntity<?> registAsset(@RequestBody AssetDTO newAsset) {
        assetService.registAsset(newAsset);

        return ResponseEntity.created(URI.create("/assets/" + newAsset.getCode())).build();
    }
}
