package com.iiiiii.accountbook.acc_group.query.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccGroupDTO {
    private int code;
    private String name;
    private String intro;

    public AccGroupDTO(String name, String intro) {
        this.name = name;
        this.intro = intro;
    }

    public AccGroupDTO(int code, String name, String intro) {
        this.code = code;
        this.name = name;
        this.intro = intro;
    }
}
