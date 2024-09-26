package com.top.effitopia.domain;

import com.top.effitopia.dto.WarehouseDTO;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseCost {

    private Integer id;

    private Warehouse warehouse;

    private int	contractCost;

    private int	storageCostPerArea;

    private int	inboundBasicFee;

    private int	outboundBasicFee;
}
