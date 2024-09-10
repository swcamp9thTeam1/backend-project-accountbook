package com.iiiiii.accbooksecurity.member.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiiiii.accbooksecurity.member.command.application.service.MemberService;
import com.iiiiii.accbooksecurity.member.vo.RequestLoginVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private MemberService memberService;
    private Environment env;

    private StandardPBEStringEncryptor encryptor;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                MemberService memberService,
                                Environment env) {
        super(authenticationManager);
//        super.setAuthenticationManager(authenticationManager);
        this.memberService = memberService;
        this.env = env;
    }

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                MemberService memberService,
                                Environment env,
                                StandardPBEStringEncryptor encryptor) {
        super(authenticationManager);
        this.memberService = memberService;
        this.env = env;
        this.encryptor = encryptor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            RequestLoginVO creds = new ObjectMapper().readValue(request.getInputStream(), RequestLoginVO.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getMemberId(), creds.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        log.info("로그인 성공하고 security가 관리하는 principal객체(authResult): {}", authResult);

        // 주석. 여기서 부터 토큰
        log.info("시크릿 키: " + env.getProperty("token.secret"));

        String userName = ((User)authResult.getPrincipal()).getUsername();
        log.info("인증된 회원의 id: " + userName);

        List<String> roles = authResult.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());


        Claims claims = Jwts.claims().setSubject(userName);
        claims.put("auth", roles);

        String secretKey = encryptor.decrypt(env.getProperty("token.secret").replace("ENC(", "").replace(")", ""));

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()
                        + Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        response.addHeader("token", token);
    }


}
