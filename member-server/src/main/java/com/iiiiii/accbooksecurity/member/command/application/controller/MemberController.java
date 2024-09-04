package com.iiiiii.accbooksecurity.member.command.application.controller;

import com.iiiiii.accbooksecurity.member.command.application.service.MemberService;
import com.iiiiii.accbooksecurity.member.command.domain.aggregate.dto.MemberDTO;

import com.iiiiii.accbooksecurity.member.security.JwtUtil;
import com.iiiiii.accbooksecurity.member.vo.*;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class MemberController {

    private ModelMapper modelMapper;
    private MemberService memberService;
    private Environment env;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public MemberController(ModelMapper modelMapper,
                            MemberService memberService,
                            Environment env,
                            AuthenticationManager authenticationManager,
                            JwtUtil jwtUtil
    ) {
        ;
        this.modelMapper = modelMapper;
        this.memberService = memberService;
        this.env = env;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // 주석. 로그인
    @PostMapping("/members/login")
    public ResponseEntity<?> login(@RequestBody RequestLoginVO loginRequest) {
        log.info("로그인 요청 - 사용자 ID: {}", loginRequest.getMemberId()); // 로그 추가
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getMemberId(), loginRequest.getPassword())
        );
        log.info("Authentication 성공: {}", authentication.getName()); // 로그 추가
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateToken(authentication);
        log.info("생성된 JWT 토큰: {}", jwt);

        // 주석. JWT 토큰을 데이터베이스에 저장
        log.info("Updating JWT Token for memberId: {}", loginRequest.getMemberId());
        memberService.updateJwtToken(loginRequest.getMemberId(), jwt);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);

        MemberDTO memberDTO = memberService.findMemberByMemberId(loginRequest.getMemberId());

        JwtResponseVO jwtResponseVO = new JwtResponseVO(jwt, memberDTO);

        return ResponseEntity.ok()
                .headers(headers)
                .body(jwtResponseVO);
    }

    // 주석. 회원가입
    @PostMapping("members")
    public ResponseEntity<String> registMember(@RequestBody RequestRegistMemberVO newUser) {
        try {
            MemberDTO memberDTO = modelMapper.map(newUser, MemberDTO.class);
            memberService.registMember(memberDTO);  // 중복된 아이디가 있으면 예외 발생

            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
        } catch (IllegalStateException e) {
            // 중복된 아이디인 경우 409 Conflict 상태 코드와 메시지 반환
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            // 그 외의 다른 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 가입에 실패했습니다.");
        }
    }

    // 주석. 회원정보 조회
    @GetMapping("/members/{memNo}")
    public ResponseEntity<ResponseFindMemberVO> getUser(
            @PathVariable("memNo") String memNo,
            @RequestHeader("Authorization") String token
    ) {

        MemberDTO memberDTO = memberService.getUserByUserId(memNo);

        ResponseFindMemberVO findUser = modelMapper.map(memberDTO, ResponseFindMemberVO.class);
        return ResponseEntity.status(HttpStatus.OK).body(findUser);
    }

    // 주석. 닉네임 변경
    @PutMapping("/members/{memNo}/nickname")
    public ResponseEntity<?> updateNickname(
            @PathVariable("memNo") String memNo,
            @RequestBody Map<String, String> request,
            @RequestHeader("Authorization") String token) {

        String newNickname = request.get("nickname");

        memberService.updateNickname(memNo, newNickname);

        return ResponseEntity.status(HttpStatus.OK).body("닉네임이 성공적으로 변경되었습니다.");
    }

    // 주석. 로그아웃
    @PostMapping("/members/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
//        String userIdFromToken = jwtUtil.getMemberId(token.replace("Bearer ", ""));
//
//
//        memberService.invalidateJwtToken(token.replace("Bearer ", ""));
//
//        return ResponseEntity.ok("로그아웃 되었습니다.");

//        String userIdFromToken = jwtUtil.getMemberId(token.replace("Bearer ", ""));

        // 토큰 무효화 처리
        memberService.invalidateJwtToken(token.replace("Bearer ", ""));

        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

//    @PostMapping("/members/refresh-token")
//    public ResponseEntity<?> refreshToken(@RequestBody String refreshToken) {
//        boolean isValid = memberService.validateRefreshToken(refreshToken);
//        if (!isValid) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token.");
//        }
////
//
//        String userId = jwtUtil.getMemberId(refreshToken);
//        UserDetails userDetails = memberService.loadUserByUsername(userId);
//
//        // Create an Authentication object
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                userDetails, null, userDetails.getAuthorities());
//
//        // Generate new JWT
//        String newJwt = jwtUtil.generateToken(authentication);
//
//        return ResponseEntity.ok(new JwtResponseVO(newJwt, refreshToken));
//    }


}
