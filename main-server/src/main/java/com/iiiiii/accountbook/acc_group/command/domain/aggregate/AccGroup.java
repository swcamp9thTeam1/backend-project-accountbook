package com.iiiiii.accountbook.acc_group.command.domain.aggregate;

import lombok.Data;

@Data
public class AccGroup {
    private int code;
    private String name;
    private String intro;

    public AccGroup(String name, String intro) {
        this.name = name;
        this.intro = intro;
    }

    public AccGroup(int code, String name, String intro) {
        this.code = code;
        this.name = name;
        this.intro = intro;
    }
}
