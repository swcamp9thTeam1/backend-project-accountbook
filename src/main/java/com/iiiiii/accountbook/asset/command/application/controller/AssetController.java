package com.iiiiii.accountbook.asset.command.application.controller;

import com.iiiiii.accountbook.asset.command.application.service.AssetService;
import com.iiiiii.accountbook.asset.command.domain.aggregate.dto.AssetDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController("AssetControllerCommand")
@RequestMapping("/assets")
@Slf4j
public class AssetController {

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetService registAsset) {
        this.assetService = registAsset;
    }

    /* 자산 등록 */
    @PostMapping("")
    public ResponseEntity<?> registAsset(@RequestBody AssetDTO newAsset) {
        assetService.registAsset(newAsset);

        return ResponseEntity.created(URI.create("/assets/" + newAsset.getCode())).build();
    }

    /* 자산 수정 */
    @PutMapping("/{code}")
    public ResponseEntity<?> modifyAsset(@RequestBody AssetDTO modifiedAsset, @PathVariable Integer code) {
        assetService.modifyAsset(code, modifiedAsset);

        return ResponseEntity.noContent().build();
    }
}
