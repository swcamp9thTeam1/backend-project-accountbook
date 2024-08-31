package com.iiiiii.accountbook.store.query.service;

import com.iiiiii.accountbook.store.common.StoreSearchCriteria;
import com.iiiiii.accountbook.store.query.dto.StoreDTO;
import com.iiiiii.accountbook.store.query.repository.StoreMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StoreService {

    private final StoreMapper storeMapper;

    @Autowired
    public StoreService(StoreMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    public List<StoreDTO> findAllStores() {
        List<StoreDTO> stores = storeMapper.selectAllStores();
//        log.info("stores: {}", stores);

        return stores;
    }

    public StoreDTO findStoreById(int storeCode) {
        StoreDTO store = storeMapper.selectStoreById(storeCode);
//        log.info("store: {}", store);

        return store;
    }

    public List<StoreDTO> searchStore(StoreSearchCriteria criteria) {
        List<StoreDTO> stores = storeMapper.searchStore(criteria);
//        log.info("stores: {}", stores);

        return stores;
    }

    public boolean isExistStoreByLatLng(String lat, String lng) {
        StoreSearchCriteria criteria = new StoreSearchCriteria();
        criteria.setLatitude(lat);
        criteria.setLongitude(lng);

        List<StoreDTO> foundStores = storeMapper.searchStore(criteria);
        return !foundStores.isEmpty();
    }
}
