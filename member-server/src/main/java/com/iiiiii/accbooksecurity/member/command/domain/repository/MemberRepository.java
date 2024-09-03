package com.iiiiii.accbooksecurity.member.command.domain.repository;

import com.iiiiii.accbooksecurity.member.command.domain.aggregate.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
        Member findByMemberId(String memberId);

        Optional<Member> findByJwtToken(String JwtToken);
}
