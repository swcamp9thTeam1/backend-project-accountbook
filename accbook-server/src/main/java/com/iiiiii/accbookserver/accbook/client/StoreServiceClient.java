package com.iiiiii.accbookserver.accbook.client;

import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.RequestRegistStoreVO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.ResponseStoreCodeVO;
import com.iiiiii.accbookserver.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="stores", url="localhost:8000", configuration = FeignClientConfig.class)
public interface StoreServiceClient {
    @GetMapping("/main-service/stores/code")
    ResponseStoreCodeVO getStoreCodeByLatLng(@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude);



    @PostMapping("/main-service/stores")
//    @PutMapping("/stores/")
    void registerStore(@RequestBody RequestRegistStoreVO requestRegistStoreVO);

}
