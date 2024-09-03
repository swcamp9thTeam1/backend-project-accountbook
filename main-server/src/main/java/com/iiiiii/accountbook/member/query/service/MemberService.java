package com.iiiiii.accountbook.member.query.service;

import com.iiiiii.accountbook.member.query.dto.MemberDTO;
import com.iiiiii.accountbook.member.query.repository.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Scanner;

@Service
@Slf4j
public class MemberService {
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Transactional
    public void registerNewMember(MemberDTO memberDTO) {

        int nicknameExists = memberMapper.existsByNickname(memberDTO.getNickname());
        int emailExists = memberMapper.existsByEmail(memberDTO.getEmail());

        System.out.println("Nickname exists: " + nicknameExists);
        System.out.println("Email exists: " + emailExists);

        // 닉네임 중복 확인
        if (memberMapper.existsByNickname(memberDTO.getNickname())>0) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");

        }

        // 이메일 중복 확인
        if (memberMapper.existsByEmail(memberDTO.getEmail())>0) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        // 새로운 회원 추가
        memberMapper.insertMember(memberDTO);
        System.out.println("회원가입이 완료되었습니다: " + memberDTO);
    }
}
