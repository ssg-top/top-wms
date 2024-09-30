package com.top.effitopia.service;

import com.top.effitopia.domain.Cell;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.dto.*;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {
    PageResponseDTO<WarehouseDTO> getWarehouseList(PageRequestDTO<Warehouse> pageRequestDTO);

    PageResponseDTO<CellDTO> getCellList(PageRequestDTO<Cell> pageRequestDTO);

    Optional<WarehouseDTO> get(Integer id);

    Optional<String> get(String name);

    boolean modify(WarehouseDTO warehouseDTO);

    boolean remove(Integer id);

    boolean save(WarehouseDTO warehouseDTO);

    Warehouse changedVO(WarehouseDTO warehouseDTO);

    WarehouseDTO changedDTO(Warehouse warehouse);

    List<WarehouseDTO> changedListDTO(List<Warehouse> warehouseList);

    List<WarehouseTypeDTO> getTypeList();
}
