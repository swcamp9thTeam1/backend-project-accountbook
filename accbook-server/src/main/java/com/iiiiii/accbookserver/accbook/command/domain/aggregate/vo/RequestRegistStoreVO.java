package com.iiiiii.accbookserver.accbook.command.domain.aggregate.vo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestRegistStoreVO {
    private String storeName;
    private String storeAddress;
    private String latitude;
    private String longitude;
}