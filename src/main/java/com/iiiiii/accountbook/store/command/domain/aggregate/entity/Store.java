package com.iiiiii.accountbook.store.command.domain.aggregate.entity;

import com.iiiiii.accountbook.common.YesOrNo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private YesOrNo isGood;

    @Column(name = "good_menu_name")
    private String goodMenuName;

    @Column(name = "good_menu_price")
    private Integer goodMenuPrice;
}
