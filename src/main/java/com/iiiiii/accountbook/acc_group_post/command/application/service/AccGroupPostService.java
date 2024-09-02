package com.iiiiii.accountbook.acc_group_post.command.application.service;

import com.iiiiii.accountbook.acc_group_post.command.domain.aggregate.vo.RegisterAccGroupPostVO;
import com.iiiiii.accountbook.exception.NotAllowedGroupRoleException;

public interface AccGroupPostService {
    int registerGroupPost(int groupCode, RegisterAccGroupPostVO registerAccGroupPostVO)
            throws Exception;
}
