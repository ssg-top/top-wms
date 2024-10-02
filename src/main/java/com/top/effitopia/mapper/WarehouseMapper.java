package com.top.effitopia.mapper;

import com.top.effitopia.domain.Cell;
import com.top.effitopia.domain.Member;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.domain.WarehouseType;
import com.top.effitopia.dto.CellDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.WarehouseDTO;
import com.top.effitopia.enumeration.MemberStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WarehouseMapper {
    List<Warehouse> selectWarehouseList(PageRequestDTO<WarehouseDTO> pageRequestDTO);
    List<Cell> selectCellList(PageRequestDTO<CellDTO> pageRequestDTO);
    int update(Warehouse warehouse);
    int delete(Integer warehouse_id);
    Warehouse selectId(Integer id);
    String selectName(String name);
    int insert(Warehouse warehouse);
    int getCount(PageRequestDTO pageRequestDTO);
    List<WarehouseType> selectAllType();
    List<Member> selectAssignableWarehouseManagerList();
}
