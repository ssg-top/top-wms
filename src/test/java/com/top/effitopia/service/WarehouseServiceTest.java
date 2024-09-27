package com.top.effitopia.service;

import com.top.effitopia.dto.CellDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.WarehouseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class WarehouseServiceTest {

    @Autowired
    private WarehouseService warehouseService;

    @Test
    public void getWarehouseListTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(2).size(10).build();
        PageResponseDTO<WarehouseDTO> responseDTO = warehouseService.getWarehouseList(pageRequestDTO);
        responseDTO.getDtoList().forEach(log::info);
    }

    @Test
    public void getCellListTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        PageResponseDTO<CellDTO> responseDTO = warehouseService.getCellList(pageRequestDTO);
        responseDTO.getDtoList().forEach(log::info);

    }
}
