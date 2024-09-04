package com.iiiiii.accbookserver.accbook.client;

import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.ResponseAccCategoryVO;
import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.ResponseRegExpVO;
import com.iiiiii.accbookserver.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="category", url="localhost:8000", configuration = FeignClientConfig.class)
public interface AccCategoryServiceClient {
    @GetMapping("/main-service/acc-category/{categoryCode}")
//    @GetMapping("/acc-category/{categoryCode}")
    ResponseAccCategoryVO findOneAccCategory(@PathVariable Integer categoryCode);
}
