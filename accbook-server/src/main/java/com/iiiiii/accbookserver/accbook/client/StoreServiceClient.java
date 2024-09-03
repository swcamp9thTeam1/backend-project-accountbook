package com.iiiiii.accbookserver.accbook.client;

import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.RequestRegistStoreVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="stores", url="localhost:8000")
public interface StoreServiceClient {
    @GetMapping("/main-service/stores/{code}/in")
//    @GetMapping("/stores/code")
    Integer getStoreCodeByLatLng(@RequestParam String lat, @RequestParam String lng);

    @PutMapping("/main-service/stores/{code}/in")
//    @PutMapping("/stores/")
    void registerStore(@RequestParam RequestRegistStoreVO requestRegistStoreVO);

}
