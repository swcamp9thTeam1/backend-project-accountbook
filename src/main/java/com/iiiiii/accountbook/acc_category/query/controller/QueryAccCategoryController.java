package com.iiiiii.accountbook.acc_category.query.controller;

import com.iiiiii.accountbook.acc_category.query.dto.QueryAccCategoryDTO;
import com.iiiiii.accountbook.acc_category.query.service.QueryAccCategoryService;
import com.iiiiii.accountbook.common.ResponseMessage;
import com.iiiiii.accountbook.common.ResponseStatusText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/acc-category")
public class QueryAccCategoryController {
    private QueryAccCategoryService queryAccCategoryService;

    @Autowired
    public QueryAccCategoryController(QueryAccCategoryService queryAccCategoryService) {
        this.queryAccCategoryService = queryAccCategoryService;
    }

    @GetMapping("/")
    public ResponseEntity<ResponseMessage> findAllAccCategory() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json",
                Charset.forName("UTF-8")));

        List<QueryAccCategoryDTO> accCategories = queryAccCategoryService.findAllAccCategory();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("accCategories", accCategories);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(ResponseStatusText.OK, "조회 성공!", responseMap));
    }

    @GetMapping("/{memberCode}")
    public ResponseEntity<ResponseMessage> findAccCategoryByMemberCode(@RequestParam("memberCode") int memberCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json",
                Charset.forName("UTF-8")));

        List<QueryAccCategoryDTO> accCategories = queryAccCategoryService.findAccCategoryByMemberCode(memberCode);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("accCategories", accCategories);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(ResponseStatusText.OK, "조회 성공!", responseMap));
    }

    @GetMapping("/codes")
    public ResponseEntity<ResponseMessage> findAccCategoryCode(@RequestParam("memberCode") int memberCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json",
                Charset.forName("UTF-8")));

        List<Integer> accCategories = queryAccCategoryService.findAllAccCategoryCode(memberCode);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("accCategoryCodes", accCategories);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(ResponseStatusText.OK, "조회 성공!", responseMap));
    }

    @GetMapping("/names")
    public ResponseEntity<ResponseMessage> findAccCategoryName(@RequestParam("memberCode") int memberCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json",
                Charset.forName("UTF-8")));

        List<String> accCategories = queryAccCategoryService.findAllAccCategoryName(memberCode);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("accCategoryNames", accCategories);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(ResponseStatusText.OK, "조회 성공!", responseMap));
    }

    @GetMapping("/in-or-out")
    public ResponseEntity<ResponseMessage> findAccCategoryByIO(@RequestParam("memberCode") int memberCode,
                                                               @RequestParam("financeType") char financeType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json",
                Charset.forName("UTF-8")));

        Map<String, Object> params = new HashMap<>();
        params.put("financeType", financeType);
        params.put("memberCode", memberCode);

        List<QueryAccCategoryDTO> accCategories = queryAccCategoryService.findAccCategoryByIO(params);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("accCategoryByInOrOut", accCategories);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(ResponseStatusText.OK, "조회 성공!", responseMap));
    }

    @GetMapping("/is-deleted")
    public ResponseEntity<ResponseMessage> findAccCategoryByFinanceType(@RequestParam("memberCode") int memberCode,
                                                                        @RequestParam("isDeleted") char isDeleted) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json",
                Charset.forName("UTF-8")));

        Map<String, Object> params = new HashMap<>();
        params.put("memberCode", memberCode);
        params.put("isDeleted", isDeleted);

        List<QueryAccCategoryDTO> accCategories = queryAccCategoryService.findAccCategoryByIsDeleted(params);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("accCategoryByIsDeleted", accCategories);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(ResponseStatusText.OK, "조회 성공!", responseMap));
    }

    @GetMapping("/visibility")
    public ResponseEntity<ResponseMessage> findAccCategoryByVisibility(@RequestParam("memberCode") int memberCode,
                                                                        @RequestParam("visibility") char visibility) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json",
                Charset.forName("UTF-8")));

        Map<String, Object> params = new HashMap<>();
        params.put("memberCode", memberCode);
        params.put("visibility", visibility);

        List<QueryAccCategoryDTO> accCategories = queryAccCategoryService.findAccCategoryByVisibility(params);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("accCategoryByVisibility", accCategories);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(ResponseStatusText.OK, "조회 성공!", responseMap));
    }
}
