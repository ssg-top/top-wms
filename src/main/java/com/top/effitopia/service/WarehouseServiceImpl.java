package com.top.effitopia.service;

import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.WarehouseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService{

    @Override
    public PageResponseDTO<WarehouseDTO> getList(PageRequestDTO pageRequestDTO) {
        return null;
    }

    @Override
    public WarehouseDTO get(Long warehouse_id) {
        return null;
    }

    @Override
    public boolean modify(WarehouseDTO warehouseDTO) {
        return false;
    }

    @Override
    public boolean remove(Long warehouse_id) {
        return false;
    }
}
