package com.iiiiii.accountbook.store.query.controller;

import com.iiiiii.accountbook.store.query.dto.StoreDTO;
import com.iiiiii.accountbook.store.query.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
