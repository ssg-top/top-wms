package com.top.effitopia.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDTO {
    private Integer orderId;
    private StockDTO stockDTO;
    @NotEmpty
    private String buyerName;
    @NotEmpty
    private String zipCode;
    @NotEmpty
    private String buyerRoadName;
    @NotEmpty
    private String buyerLotNumber;
    @NotEmpty
    private String buyerDetailAddress;
    @Digits(integer = 9, fraction = 6)
    @Positive
    private double buyerLatitude;
    @Digits(integer = 9, fraction = 6)
    @Positive
    private double buyerLongitude;
    @NotNull
    @Positive
    private Integer buyerQuantity;
}
