package com.iiiiii.accbooksecurity.member.command.application.controller;

import com.iiiiii.accbooksecurity.member.Hello;
import com.iiiiii.accbooksecurity.member.command.application.service.MemberService;
import com.iiiiii.accbooksecurity.member.command.domain.aggregate.dto.MemberDTO;

import com.iiiiii.accbooksecurity.member.security.JwtUtil;
import com.iiiiii.accbooksecurity.member.vo.*;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class MemberController {

    private ModelMapper modelMapper;
    private MemberService memberService;
    private Environment env;
    private Hello hello;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public MemberController(ModelMapper modelMapper,
                            MemberService memberService,
                            Environment env,
                            Hello hello,
                            AuthenticationManager authenticationManager,
                            JwtUtil jwtUtil
    ) {
        ;
        this.modelMapper = modelMapper;
        this.memberService = memberService;
        this.env = env;
        this.hello = hello;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

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

        // JWT 토큰을 데이터베이스에 저장
        log.info("Updating JWT Token for memberId: {}", loginRequest.getMemberId());
        memberService.updateJwtToken(loginRequest.getMemberId(), jwt);

        return ResponseEntity.ok(new JwtResponseVO(jwt));
    }

    /* 설명. 회원가입 */
    @PostMapping("members")
    public ResponseEntity<ResponseRegistMemberVO> registMember(@RequestBody RequestRegistMemberVO newUser) {
        MemberDTO memberDTO = modelMapper.map(newUser, MemberDTO.class);

        memberService.registMember(memberDTO);

        ResponseRegistMemberVO responseMember = modelMapper.map(memberDTO, ResponseRegistMemberVO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMember);
    }


    @GetMapping("/members/{memNo}")
    public ResponseEntity<ResponseFindMemberVO> getUser(
            @PathVariable("memNo") String memNo,
            @RequestHeader("Authorization") String token
    ) {

//         토큰에서 사용자 ID 추출
        String userIdFromToken = jwtUtil.getMemberId(token.replace("Bearer ", ""));

        // memNo로 사용자의 ID를 가져옴
        MemberDTO memberDTO = memberService.getUserByUserId(memNo);

//         토큰에서 가져온 ID와 memNo의 사용자가 일치하는지 확인
        if (!memberDTO.getMemberId().equals(userIdFromToken)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 일치하지 않으면 403 Forbidden
        }

        ResponseFindMemberVO findUser = modelMapper.map(memberDTO, ResponseFindMemberVO.class);
        return ResponseEntity.status(HttpStatus.OK).body(findUser);
    }

    @PutMapping("/members/{memNo}/nickname")
    public ResponseEntity<?> updateNickname(
            @PathVariable("memNo") String memNo,
            @RequestBody Map<String, String> request) {

        String newNickname = request.get("nickname");

        // 닉네임을 업데이트하는 서비스 메서드를 호출합니다.
        memberService.updateNickname(memNo, newNickname);

        return ResponseEntity.status(HttpStatus.OK).body("닉네임이 성공적으로 변경되었습니다.");
    }

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
