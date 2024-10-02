package com.top.effitopia.service;

import com.top.effitopia.domain.Cell;
import com.top.effitopia.domain.Stock;
import com.top.effitopia.domain.StockCheck;
import com.top.effitopia.dto.*;
import com.top.effitopia.mapper.StockCheckMapper;
import com.top.effitopia.mapper.StockMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 재고실사 Service Impl
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class StockCheckServiceImpl implements StockCheckService{

    private final ModelMapper modelMapper;
    private final StockCheckMapper stockCheckMapper;
    private final StockMapper stockMapper;

    @Override
    public void saveStockCheck(StockCheckDTO stockCheckDTO) {
        StockCheck stockCheck = modelMapper.map(stockCheckDTO, StockCheck.class);
        stockCheckMapper.insertStockCheck(stockCheck);
    }

    @Override
    public void modifyStockCheck(StockCheckDTO stockCheckDTO) {
        StockCheck stockCheck = modelMapper.map(stockCheckDTO, StockCheck.class);
        stockCheckMapper.updateStockCheck(stockCheck);
    }

    @Override
    public void removeStockCheck(int id) {
        stockCheckMapper.deleteStockCheck(id);
    }

    @Override
    public StockCheckDTO getstockCheck(int id) {
        Optional<StockCheck> optionalStockCheck = stockCheckMapper.selectOneStockCheck(id);

        if(optionalStockCheck.isPresent()) {
            return modelMapper.map(optionalStockCheck.get(), StockCheckDTO.class);
        }
        else {
            return null;
        }
    }

    @Override
    public void saveListStockCheck(List<StockCheckDTO> stockCheckDTOList) {
        log.info("saveListStockCheck check Service............");
        List<StockCheck> stockCheckList = new ArrayList<>();
        stockCheckDTOList.forEach(stockCheckDTO -> {
            log.info(stockCheckDTO);

            Cell cell = stockMapper.selectOneStock(stockCheckDTO.getStockDTO().getId()).get().getCell();
            log.info("cell : " + cell);
            Stock stock = Stock.builder().id(stockCheckDTO.getStockDTO().getId()).build();

            StockCheck stockCheck = StockCheck.builder()
                    .stock(stock)
                    .cell(cell)
                    .checkAmount(stockCheckDTO.getCheckAmount())
                    .checkComment(stockCheckDTO.getCheckComment()).build();

            stockCheckList.add(stockCheck);
        });

        stockCheckList.forEach(stockCheck -> log.info("stockcheck : " +stockCheck));
        stockCheckMapper.insertListStockCheck(stockCheckList);
    }

    @Override
    public void modifyListStockCheck(List<StockCheckDTO> stockCheckDTOList) {
        List<StockCheck> stockCheckList = stockCheckDTOList.stream().map(stockCheckDTO -> modelMapper.map(stockCheckDTO, StockCheck.class)).collect(Collectors.toList());
        stockCheckMapper.updateListStockCheck(stockCheckList);
    }

    @Override
    public void removeListStockCheck(List<Integer> idList) {
        stockCheckMapper.deleteListStockCheck(idList);
    }



    @Override
    public PageResponseDTO<StockCheckDTO> getListStockCheck(PageRequestDTO<StockSearchCond> pageRequestDTO) {
        List<StockCheck> stockCheckList = stockCheckMapper.selectListStockCheck(pageRequestDTO);
        List<StockCheckDTO> stockCheckDTOList = new ArrayList<>();

        stockCheckList.forEach(stockCheck -> {
            MemberDTO memberDTO = MemberDTO.builder()
                    .id(stockCheck.getStock().getMember().getId())
                    .name(stockCheck.getStock().getMember().getName())
                    .email(stockCheck.getStock().getMember().getEmail())
                    .phone(stockCheck.getStock().getMember().getPhone())
                    .businessNumber(stockCheck.getStock().getMember().getBusinessNumber()).build();

            StockDTO stockDTO = StockDTO.builder()
                    .id(stockCheck.getStock().getId())
                    .stockAmount(stockCheck.getStock().getStockAmount())
                    .manufacturingDate(stockCheck.getStock().getManufacturingDate())
                    .expirationDate(stockCheck.getStock().getExpirationDate())
                    .memberDTO(memberDTO).build();

            WarehouseDTO warehouseDTO = WarehouseDTO.builder().
                    id(stockCheck.getCell().getWarehouse().getId())
                    .name(stockCheck.getCell().getWarehouse().getName())
                    .roadName(stockCheck.getCell().getWarehouse().getRoadName())
                    .zipCode(stockCheck.getCell().getWarehouse().getZipCode())
                    .lotNumber(stockCheck.getCell().getWarehouse().getLotNumber()).build();

            CellDTO cellDTO = CellDTO.builder()
                    .id(stockCheck.getCell().getId())
                    .warehouseDTO(warehouseDTO)
                    .capacity(stockCheck.getCell().getCapacity()).build();


            StockCheckDTO stockCheckDTO = StockCheckDTO.builder()
                    .id(stockCheck.getId())
                    .checkAmount(stockCheck.getCheckAmount())
                    .checkComment(stockCheck.getCheckComment())
                    .checkState(stockCheck.isCheckState())
                    .checkDate(stockCheck.getCheckDate())
                    .applyDate(stockCheck.getApplyDate())
                    .stockDTO(stockDTO)
                    .cellDTO(cellDTO).build();

            stockCheckDTOList.add(stockCheckDTO);
        });

        int total = stockCheckMapper.getStockCheckCount(pageRequestDTO);
        PageResponseDTO pageResponseDTO = new PageResponseDTO<>(pageRequestDTO, stockCheckDTOList, total);
        log.info("pageResponseDTO Service!!!!!!!!!!!!!!!!!!!!: " + pageResponseDTO);
        return pageResponseDTO;
    }

    @Override
    public void applyListStockCheck(List<StockCheckDTO> stockCheckDTOList) {
        List<StockCheck> stockCheckList = stockCheckDTOList.stream().map(stockCheckDTO-> modelMapper.map(stockCheckDTO, StockCheck.class)).collect(Collectors.toList());
        stockCheckMapper.applyList(stockCheckList);
    }
}
