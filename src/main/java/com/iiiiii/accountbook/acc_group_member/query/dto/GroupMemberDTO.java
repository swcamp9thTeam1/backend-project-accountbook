package com.iiiiii.accountbook.acc_group_member.query.dto;

import com.iiiiii.accountbook.common.GroupRole;
import lombok.Data;

@Data
public class GroupMemberDTO {
    private int memberCode;
    private int accGroupCode;
    private GroupRole role;
}
