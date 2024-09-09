package com.iiiiii.accountbook.store.common;

import com.iiiiii.accountbook.common.YesOrNo;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StoreSearchCriteria {
    private YesOrNo isGood;
    private Boolean isManyReview;
    private String longitude;
    private String latitude;

    public StoreSearchCriteria(YesOrNo isGood) {
        this.isGood = isGood;
    }

    public StoreSearchCriteria(YesOrNo isGood, Boolean isManyReview) {
        this.isGood = isGood;
        this.isManyReview = isManyReview;
    }

    public StoreSearchCriteria(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
