package com.iiiiii.accountbook.community.command.domain.repository;

import com.iiiiii.accountbook.community.command.domain.aggregate.entity.CommunityPostScrap;
import com.iiiiii.accountbook.community.command.domain.aggregate.entity.CommunityPostScrapId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostScrapRepository extends JpaRepository<CommunityPostScrap, Integer> {
}
