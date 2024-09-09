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
        return storeMapper.selectAllStores();
    }

    public StoreDTO findStoreById(int storeCode) {
        return storeMapper.selectStoreById(storeCode);
    }

    public List<StoreDTO> searchStore(StoreSearchCriteria criteria) {
        return storeMapper.searchStore(criteria);
    }

    public Integer findStoreCodeByLatLng(StoreSearchCriteria criteria) {
        List<StoreDTO> foundStores = storeMapper.searchStore(criteria);
        if (foundStores.isEmpty()) {
            return null;
        }

        return foundStores.get(0).getStoreCode();
    }
}
