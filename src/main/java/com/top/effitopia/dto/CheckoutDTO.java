package com.top.effitopia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CheckoutDTO {
    private Integer id;
    private WarehouseDTO warehouseDTO;
    private LocalDateTime regDate;
    private String checkoutWarehouseCode;
    private String checkoutName;
}
