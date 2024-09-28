package com.top.effitopia.domain;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer orderId;
    private Stock stock;
    private Member member;
    private String buyerName;
    private String zipCode;
    private String buyerRoadName;
    private String buyerLotNumber;
    private String buyerDetailAddress;
    private double buyerLatitude;
    private double buyerLongitude;
    private int buyerQuantity;
}
