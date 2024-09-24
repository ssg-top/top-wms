package com.top.effitopia.mapper;

import com.top.effitopia.domain.Warehouse;

import java.util.Optional;

public interface WarehouseMapper {
    Optional<Warehouse> selectList(WarehouseRequestDTO pageRequestDTO);
    Optional<Warehouse> update(Warehouse warehouse);
    int delete(Long warehouse_id);
}
