package com.iiiiii.accountbook.store.query.controller;

import com.iiiiii.accountbook.store.common.StoreSearchCriteria;
import com.iiiiii.accountbook.store.query.dto.StoreDTO;
import com.iiiiii.accountbook.store.query.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/")
    public List<StoreDTO> findAllStores() {
        return storeService.findAllStores();
    }

    @GetMapping("/{storeCode}")
    public StoreDTO findStoreById(@PathVariable int storeCode) {
        return storeService.findStoreById(storeCode);
    }

    @GetMapping("/search")
    public List<StoreDTO> searchStore(@RequestParam Boolean isGood, @RequestParam Boolean isManyReview) {
        StoreSearchCriteria criteria = new StoreSearchCriteria();

        criteria.setIsGood(isGood);
        criteria.setIsManyReview(isManyReview);

        return storeService.searchStore(criteria);
    }
}
