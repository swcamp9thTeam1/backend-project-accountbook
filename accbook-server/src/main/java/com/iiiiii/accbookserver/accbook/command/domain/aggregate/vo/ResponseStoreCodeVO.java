package com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseStoreCodeVO {
//    private Integer storeCode;
    private Map<String, Object> result;
}
