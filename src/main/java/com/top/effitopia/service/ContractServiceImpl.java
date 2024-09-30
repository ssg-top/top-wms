package com.top.effitopia.service;

import com.top.effitopia.domain.Contract;
import com.top.effitopia.domain.Member;
import com.top.effitopia.domain.Warehouse;
import com.top.effitopia.domain.WarehouseCost;
import com.top.effitopia.dto.ContractDTO;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.WarehouseCostDTO;
import com.top.effitopia.mapper.ContractMapper;
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
public class ContractServiceImpl implements ContractService{

    private final ModelMapper modelMapper;

    private final ContractMapper contractMapper;

    @Override
    public boolean save(ContractDTO contractDTO) {
        Contract contract = changedVO(contractDTO);
        return contractMapper.insert(contract) > 0;
    }

    @Override
    public PageResponseDTO getListAll(PageRequestDTO pageRequestDTO) {
        List<Contract> contractList = contractMapper.selectListAll(pageRequestDTO);
        List<ContractDTO> contractDTOList = contractList.stream().map(vo->modelMapper.map(vo, ContractDTO.class)).collect(Collectors.toList());
        int total = contractMapper.getCount(pageRequestDTO);
        PageResponseDTO pageResponseDTO = PageResponseDTO
                .<ContractDTO>withAll()
                .dtoList(contractDTOList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

    @Override
    public PageResponseDTO getListByStatus(PageRequestDTO pageRequestDTO) {
        List<Contract> contractList = contractMapper.selectListByStatus(pageRequestDTO);
        List<ContractDTO> contractDTOList = contractList.stream().map(vo->modelMapper.map(vo, ContractDTO.class)).collect(Collectors.toList());
        int total = contractMapper.getCount(pageRequestDTO);
        PageResponseDTO pageResponseDTO = PageResponseDTO
                .<ContractDTO>withAll()
                .dtoList(contractDTOList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        return pageResponseDTO;
    }

    @Override
    public boolean modify(ContractDTO contractDTO) {
        Contract contract = modelMapper.map(contractDTO, Contract.class);
        return contractMapper.update(contract) > 0;
    }

    @Override
    public void modifyApprovalList(List<Integer> id) {
        contractMapper.updateApprovalList(id);
    }

    @Override
    public void modifyRejectList(List<Integer> id) {
        contractMapper.updateRejectList(id);
    }

    @Override
    public Contract changedVO(ContractDTO contractDTO) {
        Warehouse warehouse = modelMapper.map((contractDTO.getWarehouseDTO()), Warehouse.class);
        Member member = modelMapper.map((contractDTO.getMemberDTO()), Member.class);

        return new Contract(contractDTO.getId(),
                warehouse,
                member,
                contractDTO.getStatus(),
                contractDTO.getStartDate(),
                contractDTO.getDate(),
                contractDTO.getEndDate(),
                contractDTO.getRegDate(),
                contractDTO.getModDate());
    }

    @Override
    public Optional<WarehouseCostDTO> get(Integer id) {
        WarehouseCost warehouseCost = contractMapper.getOne(id);
        WarehouseCostDTO warehouseCostDTO = modelMapper.map(warehouseCost, WarehouseCostDTO.class);
        return Optional.ofNullable(warehouseCostDTO);
    }
}
