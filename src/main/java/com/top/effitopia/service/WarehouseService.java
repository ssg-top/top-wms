package com.top.effitopia.service;

import com.top.effitopia.dto.CellDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.WarehouseDTO;

import java.util.Optional;

public interface WarehouseService {
    PageResponseDTO<WarehouseDTO> getWarehouseList(PageRequestDTO<WarehouseDTO> pageRequestDTO);

    PageResponseDTO<CellDTO> getCellList(PageRequestDTO<WarehouseDTO> pageRequestDTO);

    Optional<WarehouseDTO> get(Integer id);

    Optional<Integer> modify(WarehouseDTO warehouseDTO);

    boolean remove(Long warehouse_id);
}
