package com.iiiiii.accountbook.member.query.service;

import com.iiiiii.accountbook.member.query.dto.MemberDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTests {

    @Autowired
    private MemberService memberService;

    private static Stream<Arguments> getMemberInfo() {
        MemberDTO memberInfo = new MemberDTO();
        memberInfo.setMemberId("user7");
        memberInfo.setEmail("user2@example.com");
        memberInfo.setMemberPassword("password7");
        memberInfo.setNickname("nick1");
        memberInfo.setIsAdmin("N");
        memberInfo.setMonthlyBudget("500000");

        return Stream.of(
                Arguments.of(memberInfo)
        );
    }

    @DisplayName("회원가입 테스트")
    @ParameterizedTest
    @MethodSource("getMemberInfo")
    void testRegisterNewMember(MemberDTO memberInfo) {
        assertDoesNotThrow(
                () -> memberService.registerNewMember(memberInfo)
        );
    }
}
