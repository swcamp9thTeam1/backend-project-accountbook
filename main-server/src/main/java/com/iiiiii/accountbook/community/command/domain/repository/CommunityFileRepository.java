package com.iiiiii.accountbook.community.command.domain.repository;

import com.iiiiii.accountbook.community.command.domain.aggregate.entity.CommunityFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityFileRepository extends JpaRepository<CommunityFile, Integer> {
}
