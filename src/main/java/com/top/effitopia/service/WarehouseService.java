package com.top.effitopia.service;

import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.WarehouseDTO;

public interface WarehouseService {
    PageResponseDTO<WarehouseDTO> getList(PageRequestDTO warehouseRequestDTO);
    WarehouseDTO get(Long warehouse_id);
    boolean modify(WarehouseDTO warehouseDTO);
    boolean remove(Long warehouse_id);
}
