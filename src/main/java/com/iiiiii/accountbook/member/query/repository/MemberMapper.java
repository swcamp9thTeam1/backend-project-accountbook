package com.iiiiii.accountbook.member.query.repository;

import com.iiiiii.accountbook.member.query.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    int existsByNickname(@Param("nickname") String nickname);

    int existsByEmail(@Param("email") String email);

    void insertMember(MemberDTO member);

}
