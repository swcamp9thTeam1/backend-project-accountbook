package com.iiiiii.accbookserver.accbook.command.domain.aggregate.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegistStoreDTO {
    private String storeName;
    private String storeAddress;
    private String latitude;
    private String longitude;
}
