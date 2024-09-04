package com.iiiiii.accountbook.acc_group_post_file.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "CommandAccGroupPostFileEntity")
@Table(name = "acc_group_post_file")
public class AccGroupPostFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private int code;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "acc_group_post_code")
    private int accGroupPostCode;
}
