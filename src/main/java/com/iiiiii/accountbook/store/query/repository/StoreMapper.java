package com.iiiiii.accountbook.store.query.repository;

import com.iiiiii.accountbook.store.query.dto.StoreDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    List<StoreDTO> selectAllStores();

    StoreDTO selectStoreById(int storeCode);
}
