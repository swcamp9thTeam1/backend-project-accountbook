package com.iiiiii.accountbook.store.common;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StoreSearchCriteria {
    private Boolean isGood;
    private Boolean isManyReview;
    private String longitude;
    private String latitude;

    public StoreSearchCriteria(Boolean isGood, Boolean isManyReview) {
        this.isGood = isGood;
        this.isManyReview = isManyReview;
    }

    public StoreSearchCriteria(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
