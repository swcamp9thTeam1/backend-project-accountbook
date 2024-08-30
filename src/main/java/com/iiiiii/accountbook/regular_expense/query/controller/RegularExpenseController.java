//package com.iiiiii.accountbook.regular_expense.query.controller;
//
//import com.iiiiii.accountbook.common.ResponseMessage;
//import com.iiiiii.accountbook.common.ResponseStatusText;
//import com.iiiiii.accountbook.regular_expense.query.dto.RegularExpenseDTO;
//import com.iiiiii.accountbook.regular_expense.query.dto.ResponseRegularExpenseDTO;
//import com.iiiiii.accountbook.regular_expense.query.service.RegularExpenseService;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.nio.charset.Charset;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/regular-expense")
//public class RegularExpenseController {
//    private RegularExpenseService regularExpenseService;
//
//    @Autowired
//    public RegularExpenseController(RegularExpenseService regularExpenseService) {
//        this.regularExpenseService = regularExpenseService;
//    }
//
//    @GetMapping("/")
//    public ResponseEntity<ResponseMessage> findAllRegularExpenses() {
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setContentType(new MediaType("application", "json"
//                , Charset.forName("UTF-8")));
//
//        List<RegularExpenseDTO> regularExpenses = regularExpenseService.findAllRegularExpenses();
//
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("regularExpenses", regularExpenses);
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .body(new ResponseMessage(ResponseStatusText.OK,"조회 성공!", responseMap));
//    }
//
//    @GetMapping("/{memberCode}")
//    public ResponseEntity<ResponseMessage> findOneMemberRegularExpenses(@PathVariable("memberCode") int memberCode) {
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setContentType(new MediaType("application", "json"
//                , Charset.forName("UTF-8")));
//
//        List<ResponseRegularExpenseDTO> regularExpenses = regularExpenseService.findOneMemberRegularExpenses(memberCode);
//
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("memberCode", memberCode);
//        responseMap.put("regularExpenses", regularExpenses);
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .body(new ResponseMessage(ResponseStatusText.OK, "조회 성공!", responseMap));
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<ResponseMessage> findOneRegularExpenses(@RequestParam("regularExpenseCode") int regularExpenseCode) {
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setContentType(new MediaType("application", "json"
//                , Charset.forName("UTF-8")));
//
//        RegularExpenseDTO regularExpenses = regularExpenseService.findOneRegularExpenses(regularExpenseCode);
//
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("regularExpenseCode", regularExpenseCode);
//        responseMap.put("regularExpenses", regularExpenses);
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .body(new ResponseMessage(ResponseStatusText.OK, "조회 성공!", responseMap));
//    }
//}
