package com.iiiiii.accbookserver.accbook.client;

import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.ResponseRegExpVO;
import com.iiiiii.accbookserver.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="regular-expense", url="localhost:8000", configuration = FeignClientConfig.class)
public interface RegularExpenseServiceClient {
    @GetMapping("/main-service/regular-expense/")
    List<ResponseRegExpVO> findAllRegularExpenses();
}