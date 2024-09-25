package com.top.effitopia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseCostDTO {

    private long id;

    private WarehouseDTO warehouseDTO;

    private int	contractCost;

    private int	storageCostPerArea;

    private int	inboundBasicFee;

    private int	outboundBasicFee;
}
