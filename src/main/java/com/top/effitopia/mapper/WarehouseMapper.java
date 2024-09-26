package com.top.effitopia.mapper;

import com.top.effitopia.domain.Cell;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface WarehouseMapper {
    List<Warehouse> selectWarehouseList(PageRequestDTO pageRequestDTO);
    List<Cell> selectCellList(Integer id, PageRequestDTO pageRequestDTO);
    int update(Warehouse warehouse);
    int delete(Integer warehouse_id);
    Warehouse select(Integer id);
    int insert(Warehouse warehouse);
}
