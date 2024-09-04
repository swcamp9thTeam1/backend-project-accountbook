package com.iiiiii.accountbook.acc_group_member.command.domain.aggregate;

import com.iiiiii.accountbook.common.GroupRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupMember {
    private int memberCode;
    private int groupCode;
    private GroupRole role;

    public GroupMember(int memberCode, int groupCode, GroupRole role) {
        this.memberCode = memberCode;
        this.groupCode = groupCode;
        this.role = role;
    }
}
