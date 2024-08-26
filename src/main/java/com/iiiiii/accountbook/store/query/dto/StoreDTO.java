package com.iiiiii.accountbook.store.query.dto;

import com.iiiiii.accountbook.common.YesOrNo;

public class StoreDTO {
    private int storeCode;
    private String storeName;
    private String address;
    private String latitude;
    private String longitude;
    private YesOrNo isGood;
    private String goodMenuName;
    private Integer goodMenuPrice;

    public StoreDTO() {
    }

    public StoreDTO(int storeCode, String storeName, String address, String latitude, String longitude, YesOrNo isGood,
                    String goodMenuName, Integer goodMenuPrice) {
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isGood = isGood;
        this.goodMenuName = goodMenuName;
        this.goodMenuPrice = goodMenuPrice;
    }

    public int getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(int storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public YesOrNo getIsGood() {
        return isGood;
    }

    public void setIsGood(YesOrNo isGood) {
        this.isGood = isGood;
    }

    public String getGoodMenuName() {
        return goodMenuName;
    }

    public void setGoodMenuName(String goodMenuName) {
        this.goodMenuName = goodMenuName;
    }

    public Integer getGoodMenuPrice() {
        return goodMenuPrice;
    }

    public void setGoodMenuPrice(Integer goodMenuPrice) {
        this.goodMenuPrice = goodMenuPrice;
    }

    @Override
    public String toString() {
        return "StoreDTO{" +
                "storeCode=" + storeCode +
                ", storeName='" + storeName + '\'' +
                ", address='" + address + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", isGood=" + isGood +
                ", goodMenuName='" + goodMenuName + '\'' +
                ", goodMenuPrice=" + goodMenuPrice +
                '}';
    }
}
