package com.iiiiii.accountbook.acc_group.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

@Data
@Entity(name = "accGroupEntity")
@Table(name = "acc_group")
public class AccGroupEntity {

    @Id
    @Column(name = "CODE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "INTRO", nullable = true)
    private String intro;
}
