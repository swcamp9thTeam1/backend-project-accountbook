package com.iiiiii.accountbook.acc_group.query.dto;

import lombok.Data;

@Data
public class QueryAccGroupDTO {
    private int code;
    private String name;
    private String intro;

    public QueryAccGroupDTO(String name, String intro) {
        this.name = name;
        this.intro = intro;
    }

    public QueryAccGroupDTO(int code, String name, String intro) {
        this.code = code;
        this.name = name;
        this.intro = intro;
    }
}
