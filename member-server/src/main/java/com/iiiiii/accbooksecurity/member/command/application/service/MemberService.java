package com.iiiiii.accbooksecurity.member.command.application.service;

import com.iiiiii.accbooksecurity.member.command.domain.aggregate.dto.MemberDTO;
import com.iiiiii.accbooksecurity.member.command.domain.aggregate.entity.Member;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    void registMember(MemberDTO memberDTO);

    MemberDTO getUserByUserId(String memNo);

    void updateJwtToken(String memberId, String jwtToken);  // 추가된 메서드

    Member getMemberById(String memNo);

    void updateNickname(String memNo, String newNickname);

//    void deleteRefreshToken(String userIdFromToken);

//    boolean validateRefreshToken(String refreshToken);

    void invalidateJwtToken(String userId);
}
