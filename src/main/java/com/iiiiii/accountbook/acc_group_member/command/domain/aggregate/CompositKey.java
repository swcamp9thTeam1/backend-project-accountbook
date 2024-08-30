package com.iiiiii.accountbook.acc_group_member.command.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositKey implements Serializable {
    private int memberCode;
    private int groupCode;
}
