package com.iiiiii.accountbook.store.common;

public class StoreSearchCriteria {
    private Boolean isGood;

    public StoreSearchCriteria() {}

    public StoreSearchCriteria(Boolean isGood) {
        this.isGood = isGood;
    }

    public Boolean getGood() {
        return isGood;
    }

    public void setGood(Boolean good) {
        isGood = good;
    }

    @Override
    public String toString() {
        return "StoreSearchCriteria{" +
                "isGood=" + isGood +
                '}';
    }
}
