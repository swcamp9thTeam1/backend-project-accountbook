package com.iiiiii.accountbook.asset.query.controller;

import com.iiiiii.accountbook.asset.query.dto.AssetDTO;
import com.iiiiii.accountbook.asset.query.service.AssetService;
import com.iiiiii.accountbook.common.AssetCategory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/{memberCode}")
    public ResponseEntity<List<AssetDTO>> findMyAllAssets(@PathVariable("memberCode") int memberCode) {

        List<AssetDTO> assetList = assetService.findMyAllAssets(memberCode);

        return ResponseEntity.status(HttpStatus.OK).body(assetList);
    }

    @GetMapping("/category")
    public ResponseEntity<List<AssetDTO>> findMyAssetsByCategory(
                                            @RequestParam("memberCode") int memberCode,
                                            @RequestParam("category") AssetCategory category) {

        List<AssetDTO> categoryAssetList = assetService.findAssetsByCategory(memberCode, category);

        return ResponseEntity.status(HttpStatus.OK).body(categoryAssetList);
    }
}
