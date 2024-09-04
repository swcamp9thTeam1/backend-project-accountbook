package com.iiiiii.accountbook.acc_group_post_file.query.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AccGroupPostFileDTO {
    private int code;
    private String name;
    private String path;
    private int accGroupPostCode;

    public AccGroupPostFileDTO(String name, String path, int accGroupPostCode) {
        this.name = name;
        this.path = path;
        this.accGroupPostCode = accGroupPostCode;
    }

    public AccGroupPostFileDTO(int code, String name, String path, int accGroupPostCode) {
        this.code = code;
        this.name = name;
        this.path = path;
        this.accGroupPostCode = accGroupPostCode;
    }
}
