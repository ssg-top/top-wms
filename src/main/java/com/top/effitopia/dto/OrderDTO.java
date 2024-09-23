package com.top.effitopia.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDTO {
    private Integer orderId;
    //    private StockDTO stockDTO;
    private String buyerName;
    private String zipCode;
    private String buyerRoadName;
    private String buyerLotNumber;
    private String buyerDetailAddress;
    private double buyerLatitude;
    private double buyerLongitude;
    private int buyerQuantity;
}
