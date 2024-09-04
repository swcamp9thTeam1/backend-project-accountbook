package com.iiiiii.accountbook.acc_group_post_file.command.domain.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AccGroupPostFile {
    private int code;
    private String name;
    private String path;
    private int accGroupPostCode;

    public AccGroupPostFile(String name, String path, int accGroupPostCode) {
        this.name = name;
        this.path = path;
        this.accGroupPostCode = accGroupPostCode;
    }

    public AccGroupPostFile(int code, String name, String path, int accGroupPostCode) {
        this.code = code;
        this.name = name;
        this.path = path;
        this.accGroupPostCode = accGroupPostCode;
    }
}
