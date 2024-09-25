package com.top.effitopia.mapper;

import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.dto.PageRequestDTO;

import java.util.Optional;

public interface WarehouseMapper {
    Optional<Warehouse> selectList(PageRequestDTO pageRequestDTO);
    Optional<Warehouse> update(Warehouse warehouse);
    int delete(Long warehouse_id);
}
