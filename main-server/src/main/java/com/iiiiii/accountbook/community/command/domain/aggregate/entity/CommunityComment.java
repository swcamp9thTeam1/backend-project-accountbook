package com.iiiiii.accountbook.community.command.domain.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CommunityComment {

    @Id
    @Column(name = "code")
    private int code;
}
