package com.iiiiii.accbooksecurity.member.command.application.service;

import com.iiiiii.accbooksecurity.member.command.domain.aggregate.dto.MemberDTO;
import com.iiiiii.accbooksecurity.member.command.domain.aggregate.entity.Member;
import com.iiiiii.accbooksecurity.member.command.domain.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    MemberRepository memberRepository;
    ModelMapper modelMapper;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             ModelMapper modelMapper,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void registMember(MemberDTO memberDTO) {

        if (memberRepository.findByMemberId(memberDTO.getMemberId()) != null) {
            throw new IllegalStateException("이미 존재하는 회원 아이디입니다.");
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Member member = modelMapper.map(memberDTO, Member.class);
        log.info("Service 계층에서 DTO -> Entity: {}", member);

        member.setEncryptedPwd(bCryptPasswordEncoder.encode(memberDTO.getPassword()));

        memberRepository.save(member);

    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        Member loginUser = memberRepository.findByMemberId(id);

        if (loginUser == null) {
            throw new UsernameNotFoundException(id + "유저는 존재하지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ENTERPRISE"));


        // memberIsAdmin 필드를 확인하여 권한 설정
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if ("Y".equals(loginUser.getMemberIsAdmin())) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return new User(loginUser.getMemberId(), loginUser.getEncryptedPwd(),
                true, true, true, true,
                grantedAuthorities);
    }

    @Override
    @Transactional
    public void updateJwtToken(String memberId, String jwtToken) {
        log.info("updateJwtToken 호출됨 - 사용자 ID: {} / 토큰: {}", memberId, jwtToken); // 로그 추가
        Member member = memberRepository.findByMemberId(memberId);
        if (member != null) {
            member.setJwtToken(jwtToken);
            memberRepository.save(member);
            log.info("JWT 토큰이 DB에 저장되었습니다: {}", jwtToken);
        } else {
            log.error("Member not found for id: {}", memberId);
            throw new UsernameNotFoundException(memberId + " 사용자를 찾을 수 없습니다.");
        }
    }


    @Override
    public MemberDTO getUserByUserId(String memNo) {
        Member member = memberRepository.findById(Integer.parseInt(memNo)).get();

        MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);

        return memberDTO;
    }

    public Member getMemberById(String memNo) {
        return memberRepository.findById(Integer.parseInt(memNo)).orElse(null);
    }

    @Override
    @Transactional
    public void updateNickname(String memNo, String newNickname) {
        // 회원 조회
        Member member = memberRepository.findById(Integer.parseInt(memNo))
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."));

        member.setMemberNickname(newNickname);
        memberRepository.save(member);
        log.info("사용자 {}의 닉네임이 {}(으)로 변경되었습니다.", memNo, newNickname);
    }

    @Transactional
    public void invalidateJwtToken(String token) {
        Optional<Member> memberOpt = memberRepository.findByJwtToken(token);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            member.setJwtToken(null);
            memberRepository.save(member);
            log.info("토큰이 무효화되었습니다.");
        } else {
            log.warn("토큰이 존재하지 않거나 이미 무효화되었습니다.");
        }
    }

    public MemberDTO findMemberByMemberId(String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        if (member == null) {
            throw new RuntimeException("Member not found with memberId: " + memberId);
        }
        return modelMapper.map(member, MemberDTO.class);
    }

//    @Override
//    public void saveRefreshToken(String memberId, String refreshToken) {
//        Member member = memberRepository.findByMemberId(memberId);
//        if (member != null) {
//            member.setRefreshToken(refreshToken);
//            memberRepository.save(member);
//        }
//    }
//
//    @Override
//    public boolean validateRefreshToken(String refreshToken){
//        Optional<Member> memberOpt = memberRepository.findByRefreshToken(refreshToken);
//        return memberOpt.isPresent();
//    }
//
//    @Override
//    public void deleteRefreshToken(String memberId) {
//        Member member = memberRepository.findByMemberId(memberId);
//        if (member != null) {
//            member.setRefreshToken(null);
//            memberRepository.save(member);
//        }
//    }
}
