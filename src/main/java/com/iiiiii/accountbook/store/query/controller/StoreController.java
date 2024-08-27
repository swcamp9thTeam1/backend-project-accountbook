package com.iiiiii.accountbook.store.query.controller;

import com.iiiiii.accountbook.common.ResponseMessage;
import com.iiiiii.accountbook.common.ResponseMessageGeneric;
import com.iiiiii.accountbook.common.ResponseStatusText;
import com.iiiiii.accountbook.store.common.StoreSearchCriteria;
import com.iiiiii.accountbook.store.query.dto.StoreDTO;
import com.iiiiii.accountbook.store.query.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseMessage> findAllStores() {
        List<StoreDTO> stores = storeService.findAllStores();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("stores", stores);

        return ResponseEntity.ok(new ResponseMessage(
                ResponseStatusText.OK,
                resultMap
        ));
    }

    @GetMapping("/{storeCode}")
    public ResponseEntity<ResponseMessageGeneric<StoreDTO>> findStoreById(@PathVariable int storeCode) {
        StoreDTO foundStore = storeService.findStoreById(storeCode);

        return ResponseEntity.ok(new ResponseMessageGeneric<>(
                ResponseStatusText.OK,
                foundStore
        ));
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseMessageGeneric<List<StoreDTO>>> searchStore(
            @RequestParam(name = "is-good") Boolean isGood,
            @RequestParam(name = "is-many-review") Boolean isManyReview) {
        StoreSearchCriteria criteria = new StoreSearchCriteria();

        criteria.setIsGood(isGood);
        criteria.setIsManyReview(isManyReview);

        List<StoreDTO> stores = storeService.searchStore(criteria);

        return ResponseEntity.ok(new ResponseMessageGeneric<>(
                ResponseStatusText.OK,
                stores
        ));
    }
}
