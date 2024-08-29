package com.iiiiii.accountbook.store.command.domain.aggregate.entity;

import com.iiiiii.accountbook.common.YesOrNo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store")
@Getter
@ToString
public class Store {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeCode;

    @Column(name = "name", nullable = false)
    private String storeName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "latitude", nullable = false)
    private String latitude;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @Column(name = "is_good", nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private YesOrNo isGood;

    @Column(name = "good_menu_name")
    @Setter
    private String goodMenuName;

    @Column(name = "good_menu_price")
    @Setter
    private Integer goodMenuPrice;

    public Store() {}

    public Store(String storeName, String address, String latitude,
                 String longitude, YesOrNo isGood, String goodMenuName,
                 Integer goodMenuPrice) {
        this.storeName = storeName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isGood = isGood;
        this.goodMenuName = goodMenuName;
        this.goodMenuPrice = goodMenuPrice;
    }

    public Store(int storeCode, String storeName, String address,
                 String latitude, String longitude, YesOrNo isGood, String goodMenuName,
                 Integer goodMenuPrice) {
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isGood = isGood;
        this.goodMenuName = goodMenuName;
        this.goodMenuPrice = goodMenuPrice;
    }
}
