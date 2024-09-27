package com.top.effitopia.service;

import com.top.effitopia.domain.Cell;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.dto.CellDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.WarehouseDTO;
import com.top.effitopia.mapper.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService{

    private final WarehouseMapper warehouseMapper;

    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<WarehouseDTO> getWarehouseList(PageRequestDTO<WarehouseDTO> pageRequestDTO) {
        List<Warehouse> warehouseList = warehouseMapper.selectWarehouseList(pageRequestDTO);
        List<WarehouseDTO> warehouseDTOList = warehouseList.stream().map(vo->modelMapper.map(vo, WarehouseDTO.class)).collect(Collectors.toList());
        int total = warehouseMapper.getCount(pageRequestDTO);
        PageResponseDTO<WarehouseDTO> pageResponseDTO = PageResponseDTO
                .<WarehouseDTO>withAll()
                .dtoList(warehouseDTOList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

    @Override
    public PageResponseDTO<CellDTO> getCellList(PageRequestDTO pageRequestDTO) {
        List<Cell> cellList = warehouseMapper.selectCellList(pageRequestDTO);
        List<CellDTO> cellDTOList = cellList.stream().map(vo->modelMapper.map(vo, CellDTO.class)).collect(Collectors.toList());
        int total = warehouseMapper.getCount(pageRequestDTO);
        PageResponseDTO<CellDTO> pageResponseDTO = PageResponseDTO
                .<CellDTO>withAll()
                .dtoList(cellDTOList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

    @Override
    public Optional<WarehouseDTO> get(Integer id) {
        Warehouse warehouse = warehouseMapper.selectId(id);
        WarehouseDTO warehouseDTO = modelMapper.map(warehouse, WarehouseDTO.class);
        return Optional.ofNullable(warehouseDTO);
    }

    @Override
    public Optional<Integer> modify(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = modelMapper.map(warehouseDTO, Warehouse.class);
        int result = warehouseMapper.update(warehouse);
        return Optional.of(result);
    }

    @Override
    public boolean remove(Long warehouse_id) {
        return false;
    }
}
