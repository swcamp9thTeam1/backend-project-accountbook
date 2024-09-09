package com.iiiiii.accountbook.store.command.domain.repository;

import com.iiiiii.accountbook.common.YesOrNo;
import com.iiiiii.accountbook.store.command.domain.aggregate.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    // command 작업에 대해 테스트코드 작성 시 사용하는 부분 (테스트코드에서만 사용하는 부분)
    List<Store> findByIsGood(YesOrNo isGood);
}
