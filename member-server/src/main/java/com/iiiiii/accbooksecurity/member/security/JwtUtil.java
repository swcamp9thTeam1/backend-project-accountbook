package com.iiiiii.accbooksecurity.member.security;

import com.iiiiii.accbooksecurity.member.command.application.service.MemberService;
import com.iiiiii.accbooksecurity.member.command.domain.aggregate.entity.Member;

import com.iiiiii.accbooksecurity.member.command.domain.repository.MemberRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtil {

    // 주석. 전체 토큰
    private final Key key;
    private MemberService memberService;
    private MemberRepository memberRepository;

    private StandardPBEStringEncryptor encryptor;

    public JwtUtil(
            StandardPBEStringEncryptor encryptor,
            @Value("${token.secret}") String secretKey,
            MemberService memberService
    ) {
        this.memberService = memberService;
        this.encryptor = encryptor;
        String decryptSecretKey = this.encryptor.decrypt(secretKey.replace("ENC(", "").replace(")", ""));

        byte[] keyBytes = Decoders.BASE64.decode(decryptSecretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);

    }

    // 주석. JWT 토큰 생성 추가
    public String generateToken(Authentication authentication) {
        log.info("generateToken 호출됨 - 사용자: {}", authentication.getName()); // 로그 추가
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 86400000); // 24시간 유효

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }


    public boolean validateToken(String token) {

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT token {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT Token claims empty {}", e);
        }

        return true;
    }

    public Authentication getAuthentication(String token){

        UserDetails userDetails = memberService.loadUserByUsername(this.getMemberId(token));

        Claims claims = parseClaims(token);
        log.info("넘어온 AccessToken claims 확인: {}", claims);

        Collection<? extends GrantedAuthority> authorities = null;
        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        } else {
            /* 설명. 클레임에서 권한 정보들 가져오기 */
            authorities =
                    Arrays.stream(claims.get("auth").toString()
                                    .replace("[", "")
                                    .replace("]", "")
                                    .split(", "))
                            .map(role -> new SimpleGrantedAuthority(role))
                            .collect(Collectors.toList());
        }

        return new UsernamePasswordAuthenticationToken(userDetails, "",  authorities);
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String getMemberId(String token) {
        return parseClaims(token).getSubject();
    }

    public String getMemberIdFromToken(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();  // 보통 subject에 id를 넣음
    }


    public long getExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .getTime() - System.currentTimeMillis();
    }
}
