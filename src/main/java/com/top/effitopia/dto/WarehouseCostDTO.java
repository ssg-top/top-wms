package com.top.effitopia.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseCostDTO {
    private long warehouse_cost_id;
    private WarehouseDTO warehouseDTO;
    private int	warehouse_contract_cost;
    private int	storage_cost;
    private int	monthly_maintenance_cost;
    private int	inbound_basic_fee;
    private int	outbound_basic_fee;
}
