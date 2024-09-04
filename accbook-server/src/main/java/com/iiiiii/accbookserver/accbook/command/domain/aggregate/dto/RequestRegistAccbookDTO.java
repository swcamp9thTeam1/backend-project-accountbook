package com.iiiiii.accbookserver.accbook.command.domain.aggregate.dto;

import com.iiiiii.accbookserver.common.YesOrNo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RequestRegistAccbookDTO {
    private String createdAt;
    private String title;
    private Long amount;
    private YesOrNo isRegular;
    private Integer memberCode;
    private Integer accCategoryCode;
    private Integer assetCode;

    private RegistStoreDTO registStoreDTO;
}
