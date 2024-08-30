package com.iiiiii.accountbook.asset.query.controller;

import com.iiiiii.accountbook.asset.query.dto.AssetDTO;
import com.iiiiii.accountbook.asset.query.service.AssetService;
import com.iiiiii.accountbook.common.ResponseMessage;
import com.iiiiii.accountbook.common.ResponseStatusText;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/{memberCode}")
    public ResponseEntity<ResponseMessage> findMyAllAssets(@PathVariable("memberCode") int memberCode) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        List<AssetDTO> assetList = assetService.findMyAllAssets(memberCode);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("memberCode", memberCode);
        responseMap.put("assetList", assetList);

        return ResponseEntity.ok().headers(headers).body(new ResponseMessage(ResponseStatusText.OK, responseMap));
    }
}
