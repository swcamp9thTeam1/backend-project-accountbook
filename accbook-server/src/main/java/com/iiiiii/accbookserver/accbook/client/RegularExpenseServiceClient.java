package com.iiiiii.accbookserver.accbook.client;

import com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo.ResponseRegExpVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="main-service", url="localhost:8000")
public interface RegularExpenseServiceClient {
    @GetMapping("/main-service/regular-expense/")
    List<ResponseRegExpVO> findAllRegularExpenses();
}