package com.iiiiii.accountbook.acc_group_member.query.dto;

import com.iiiiii.accountbook.common.GroupRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupMemberDTO {
    private int memberCode;
    private int accGroupCode;
    private GroupRole role;

    public GroupMemberDTO(int memberCode, int accGroupCode, GroupRole role) {
        this.memberCode = memberCode;
        this.accGroupCode = accGroupCode;
        this.role = role;
    }
}
