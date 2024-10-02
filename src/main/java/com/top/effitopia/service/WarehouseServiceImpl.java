package com.top.effitopia.service;

import com.top.effitopia.domain.Cell;
import com.top.effitopia.domain.Member;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.domain.WarehouseType;
import com.top.effitopia.dto.*;
import com.top.effitopia.mapper.WarehouseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
        List<Warehouse> warehouseList =  warehouseMapper.selectWarehouseList(pageRequestDTO);
        List<WarehouseDTO> warehouseDTOList = changedListDTO(warehouseList);
        log.info(warehouseDTOList);
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
    public PageResponseDTO<CellDTO> getCellList(PageRequestDTO<CellDTO> pageRequestDTO) {
        log.info("요긴뎅"+pageRequestDTO);
        List<Cell> cellList = warehouseMapper.selectCellList(pageRequestDTO);
        log.info("dsjkfdhskfdskfndsnfldsnflkdsn" + cellList);
        List<CellDTO> cellDTOList = cellList.stream().map(vo->modelMapper.map(vo, CellDTO.class)).collect(Collectors.toList());
        log.info("ekekiekekekekekekekek" + cellDTOList);
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
        WarehouseDTO warehouseDTO = changedDTO(warehouse);
        return Optional.ofNullable(warehouseDTO);
    }

    @Override
    public Optional<String> get(String name) {
        String result = warehouseMapper.selectName(name);
        return Optional.ofNullable(result);
    }

    @Override
    public boolean modify(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = changedVO(warehouseDTO);
        return warehouseMapper.update(warehouse) > 0;
    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }

    @Override
    public boolean save(WarehouseDTO warehouseDTO) {
        double changedLatitude = changedCoordinates(warehouseDTO.getLatitude());
        double changedLongitude = changedCoordinates(warehouseDTO.getLongitude());

        warehouseDTO.setLatitude(changedLatitude);
        warehouseDTO.setLongitude(changedLongitude);

        Warehouse warehouse = changedVO(warehouseDTO);
        log.info(warehouse);
        return warehouseMapper.insert(warehouse) > 0;
    }

    @Override
    public Warehouse changedVO(WarehouseDTO warehouseDTO) {
        Member memberVO = modelMapper.map((warehouseDTO.getMemberDTO()), Member.class);
        WarehouseType warehouseType = modelMapper.map((warehouseDTO.getWarehouseType()), WarehouseType.class);

        return new Warehouse(warehouseDTO.getId(),
                memberVO,
                warehouseType,
                warehouseDTO.getCode(),
                warehouseDTO.getName(),
                warehouseDTO.getPhone(),
                warehouseDTO.getZipCode(),
                warehouseDTO.getRoadName(),
                warehouseDTO.getLotNumber(),
                warehouseDTO.getDetailAddress(),
                warehouseDTO.getWidth(),
                warehouseDTO.getLength(),
                warehouseDTO.getHeight(),
                warehouseDTO.getCapacity(),
                warehouseDTO.getLongitude(),
                warehouseDTO.getLatitude(),
                warehouseDTO.getRegDate(),
                warehouseDTO.getModDate());
    }

    @Override
    public WarehouseDTO changedDTO(Warehouse warehouse) {
        MemberDTO memberDTO = modelMapper.map((warehouse.getMember()), MemberDTO.class);
        WarehouseTypeDTO warehouseTypeDTO = modelMapper.map((warehouse.getWarehouseType()), WarehouseTypeDTO.class);

        return new WarehouseDTO(warehouse.getId(),
                memberDTO,
                warehouseTypeDTO,
                warehouse.getCode(),
                warehouse.getName(),
                warehouse.getPhone(),
                warehouse.getZipCode(),
                warehouse.getRoadName(),
                warehouse.getLotNumber(),
                warehouse.getDetailAddress(),
                warehouse.getWidth(),
                warehouse.getLength(),
                warehouse.getHeight(),
                warehouse.getCapacity(),
                warehouse.getLongitude(),
                warehouse.getLatitude(),
                warehouse.getRegDate(),
                warehouse.getModDate());
    }

    @Override
    public List<WarehouseDTO> changedListDTO(List<Warehouse> warehouseList) {
        List<WarehouseDTO> warehouseDTOList = new ArrayList<>();

        warehouseList.stream()
                .forEach(warehouse -> {
                    MemberDTO memberDTO = MemberDTO.builder()
                            .id(warehouse.getMember().getId())
                            .username(warehouse.getMember().getUsername())
                            .build();
                    WarehouseDTO warehouseDTO = modelMapper.map(warehouse, WarehouseDTO.class);
                    warehouseDTO.setMemberDTO(memberDTO);
                    warehouseDTOList.add(warehouseDTO);
                });

        return warehouseDTOList;
        }

    @Override
    public List<WarehouseTypeDTO> getTypeList() {
        List<WarehouseType> typeList = warehouseMapper.selectAllType();
        List<WarehouseTypeDTO> typeDTOList = typeList.
                stream().
                map(vo -> modelMapper.map(vo, WarehouseTypeDTO.class)).collect(Collectors.toList());
        return typeDTOList;
    }

    @Override
    public double getTotalUtilizationAverage() {
        return warehouseMapper.getTotalUtilizationAverage();
    }

    @Override
    public List<WarehouseUtilizationDTO> getWarehouseUtilizationList() {
        return warehouseMapper.getWarehouseUtilizationList();

    public List<MemberDTO> getAssignableWarehouseManagerList() {
        List<Member> memberList = warehouseMapper.selectAssignableWarehouseManagerList();
        List<MemberDTO> memberDTOList = memberList.stream().map(MemberDTO::from).collect(Collectors.toList());
        return memberDTOList;
    }

    public Double changedCoordinates(double coordinates){
        DecimalFormat df = new DecimalFormat("#.######");
        String changedCoordinate = df.format(coordinates);

        return Double.parseDouble(changedCoordinate);

    }
}
