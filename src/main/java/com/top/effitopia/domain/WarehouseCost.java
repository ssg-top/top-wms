package com.top.effitopia.domain;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseCost {
    private long warehouse_cost_id;
    private Warehouse warehouse;
    private int	warehouse_contract_cost;
    private int	storage_cost;
    private int	monthly_maintenance_cost;
    private int	inbound_basic_fee;
    private int	outbound_basic_fee;
}
