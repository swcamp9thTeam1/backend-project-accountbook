package com.iiiiii.accountbook.acc_group_post.command.application.service;

import com.iiiiii.accountbook.acc_group_member.query.dto.GroupMemberDTO;
import com.iiiiii.accountbook.acc_group_member.query.service.QueryGroupMemberService;
import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.entity.AccGroupPost;
import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.vo.RegisterAccGroupPostVO;
import com.iiiiii.accountbook.acc_group_post.command.domain.repository.AccGroupPostRepository;
import com.iiiiii.accountbook.common.GroupRole;
import com.iiiiii.accountbook.exception.NotAllowedGroupRoleException;
import com.iiiiii.accountbook.exception.NotGroupMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service("GroupPostServiceCommand")
public class AccGroupPostServiceImpl implements AccGroupPostService {

    private final AccGroupPostRepository accGroupPostRepository;
    private final QueryGroupMemberService queryGroupMemberService;

    @Autowired
    public AccGroupPostServiceImpl(
            AccGroupPostRepository accGroupPostRepository,
            QueryGroupMemberService queryGroupMemberService) {
        this.accGroupPostRepository = accGroupPostRepository;
        this.queryGroupMemberService = queryGroupMemberService;
    }

    @Override
    public int registerGroupPost(int groupCode, RegisterAccGroupPostVO registerAccGroupPostVO)
            throws Exception {

        // 해당 그룹의 회원인지 체크
        int postOwnerCode = registerAccGroupPostVO.getMemberCode();
        List<GroupMemberDTO> groupMembers = queryGroupMemberService.findMemberByGroupCode(groupCode);
        List<GroupMemberDTO> filterGroupMembers = groupMembers.stream()
                                                    .filter(groupMember -> groupMember.getMemberCode() == postOwnerCode)
                                                    .collect(Collectors.toList());

        if (filterGroupMembers.size() != 1) {
            throw new NotGroupMemberException();
        }

        // 그룹에 승인 허용된 상태인지 체크
        GroupRole postOwnerRole = filterGroupMembers.get(0).getRole();
        if (postOwnerRole == GroupRole.ROLE_UNALLOWED) {
            throw new NotAllowedGroupRoleException();
        }

        // 게시글 생성
        AccGroupPost newAccGroupPost = new AccGroupPost();
        newAccGroupPost.setCreatedAt(LocalDateTime.now().toString());
        newAccGroupPost.setTitle(registerAccGroupPostVO.getPostTitle());
        newAccGroupPost.setDetail(registerAccGroupPostVO.getPostDetail());
        newAccGroupPost.setAccGroupCode(groupCode);
        newAccGroupPost.setMemberCode(registerAccGroupPostVO.getMemberCode());

        AccGroupPost result = accGroupPostRepository.save(newAccGroupPost);
        return result.getMemberCode();
    }
}
