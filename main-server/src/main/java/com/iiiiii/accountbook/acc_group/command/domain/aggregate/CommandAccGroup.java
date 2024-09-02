package com.iiiiii.accountbook.acc_group.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class CommandAccGroup {
    private int code;
    private String name;
    private String intro;

    public CommandAccGroup(String name, String intro) {
        this.name = name;
        this.intro = intro;
    }

    public CommandAccGroup(int code, String name, String intro) {
        this.code = code;
        this.name = name;
        this.intro = intro;
    }
}
