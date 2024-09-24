package com.top.effitopia.service;

import com.top.effitopia.dto.WarehouseDTO;

public interface WarehouseService {
    WarehouseResponseDTO<WarehouseDTO> getList(WarehouseRequestDTO warehouseRequestDTO);
    WarehouseDTO get(Long warehouse_id);
    boolean modify(WarehouseDTO warehouseDTO);
    boolean remove(Long warehouse_id);
}
