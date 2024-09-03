package com.iiiiii.accbooksecurity.member.security;

import com.iiiiii.accbooksecurity.member.command.application.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    // 주석. 전체 토큰
    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    public JwtFilter(MemberService memberService, JwtUtil jwtUtil) {
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("UsernamePasswordAuthenticationFilter보다 먼저 동작하는 필터");

        String authorizationHeader = request.getHeader("Authorization");
        log.info("Authorization header: {}", authorizationHeader);

        /* 설명. JWT 토큰이 Request Header에 있는 경우(로그인 이후 요청일 경우) */
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);        // 주석. "Bearer "를 제외한 뒤에 토큰 부분만 추출
            log.info("토큰 값: " + token);
            if (jwtUtil.validateToken(token)) {
                Authentication authentication = jwtUtil.getAuthentication(token);
                log.info("JwtFilter를 통과한 유효한 토큰을 통해 security가 관리할 principal 객체: {}", authentication.getPrincipal());
                SecurityContextHolder.getContext().setAuthentication(authentication);   // 주석. 인증이 완료되었고 이후 필터 건너뜀
            }
        }

        /* 설명. 위의 if문으로 인증된 Authentication 객체가 principal 객제로 관리되지 않는다면 다음 필터 실행 */
        filterChain.doFilter(request, response);        //
    }
}
