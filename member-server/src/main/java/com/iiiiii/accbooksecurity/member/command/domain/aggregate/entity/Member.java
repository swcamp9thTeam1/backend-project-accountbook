package com.iiiiii.accbooksecurity.member.command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="member")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @Column(name="id", nullable = false)
    private String memberId;

    @Column(name="nickname", nullable = false)
    private String memberNickname;

    @Column(name="email", nullable = false)
    private String memberEmail;

    @Column(name="password", nullable = false)
//    private String MemberPassword;
    private String encryptedPwd;

    @Column(name="is_admin", nullable = false)
    private String memberIsAdmin;

    @Column(name="monthly_budget", nullable = false)
    private Long memberMonthlyBudget;

    @Column(name="jwt_token", nullable = true)
    private String jwtToken;  // 추가된 필드

    @Column(name="refresh_token", nullable = true)
    private String refreshToken;
}
