package com.iiiiii.accbookserver.accbook.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="assets", url="localhost:8000")
public interface AssetServiceClient {
    @PutMapping("/main-service/assets/{code}/in")
//    @PutMapping("/assets/{code}/in")
    void modifyAssetByIn(@PathVariable Integer code, Long amount);

    @PutMapping("/main-service/assets/{code}/out")
//    @PutMapping("/assets/{code}/out")
    void modifyAssetByOut(@PathVariable Integer code, Long amount);
}
