package com.iiiiii.accountbook.acc_group_member.command.domain.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CompositKey implements Serializable {
    private int memberCode;
    private int groupCode;

    public CompositKey(int memberCode, int groupCode) {
        this.memberCode = memberCode;
        this.groupCode = groupCode;
    }
}
