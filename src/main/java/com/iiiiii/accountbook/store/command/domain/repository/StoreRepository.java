package com.iiiiii.accountbook.store.command.domain.repository;

import com.iiiiii.accountbook.store.command.domain.aggregate.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    long countByLatitudeAndLongitude(String latitude, String longitude);

    List<Store> findByLatitudeAndLongitude(String latitude, String longitude);
}
