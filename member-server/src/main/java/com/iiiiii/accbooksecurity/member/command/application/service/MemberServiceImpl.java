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

        /* 설명. 경우에 따라 ModelMapper는 자의적인 판단으로 필드끼리 매핑하는 경우가 있어 정확히 일치되게 매칭할려면 추가할 속성 */
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Member member = modelMapper.map(memberDTO, Member.class);
        log.info("Service 계층에서 DTO -> Entity: {}", member);

        /* 설명. BCryptPasswordEncoder 주입 후 암호화(평문 -> 다이제스트) */
        member.setEncryptedPwd(bCryptPasswordEncoder.encode(memberDTO.getPassword()));

        memberRepository.save(member);
    }

    /* 설명. 로그인 시 security가 자동으로 호출하는 메소드 */
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        /* 설명. 넘어온 email이 사용자가 입력한 id로써 email로 회원을 조회하는 쿼리 메소드 작성 */
        Member loginUser = memberRepository.findByMemberId(id);

        if (loginUser == null) {
            throw new UsernameNotFoundException(id + " 이메일 아이디의 유저는 존재하지 않습니다.");
        }

        /* 설명. 사용자의 권한들을 가져왔다는 가정 */
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

        // 닉네임 업데이트
        member.setMemberNickname(newNickname);

        // 변경 사항 저장
        memberRepository.save(member);
        log.info("사용자 {}의 닉네임이 {}(으)로 변경되었습니다.", memNo, newNickname);
    }

    // JWT 토큰을 무효화하는 메서드
    @Transactional
    public void invalidateJwtToken(String token) {
        Optional<Member> memberOpt = memberRepository.findByJwtToken(token);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            member.setJwtToken(null);  // 토큰을 무효화 처리
            memberRepository.save(member);
            log.info("토큰이 무효화되었습니다.");
        } else {
            log.warn("토큰이 존재하지 않거나 이미 무효화되었습니다.");
        }
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
