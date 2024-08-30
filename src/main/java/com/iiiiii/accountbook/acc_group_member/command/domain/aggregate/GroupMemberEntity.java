package com.iiiiii.accountbook.acc_group_member.command.domain.aggregate;

import com.iiiiii.accountbook.common.GroupRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "GroupMemberEntity")
@Table(name = "acc_group_member")
@IdClass(CompositKey.class)
public class GroupMemberEntity {

    @Id
    @Column(name = "member_code")
    private int memberCode;

    @Id
    @Column(name = "acc_group_code")
    private int groupCode;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private GroupRole role;

}
