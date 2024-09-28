package com.top.effitopia.mapper;

import com.top.effitopia.domain.Cell;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.dto.CellDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.WarehouseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface WarehouseMapper {
    List<Warehouse> selectWarehouseList(PageRequestDTO<Warehouse> pageRequestDTO);
    List<Cell> selectCellList(PageRequestDTO<Cell> pageRequestDTO);
    int update(Warehouse warehouse);
    int delete(Integer warehouse_id);
    Warehouse selectId(Integer id);
    Warehouse selectName(String name);
    int insert(Warehouse warehouse);
    int getCount(PageRequestDTO pageRequestDTO);
}
