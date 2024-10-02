package com.top.effitopia.service;

import com.top.effitopia.domain.Stock;
import com.top.effitopia.domain.TempStock;
import com.top.effitopia.dto.*;
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
 * 재고 Service Impl
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class StockServiceImpl implements StockService{

    private final ModelMapper modelMapper;
    private final StockMapper stockMapper;
    @Override
    public PageResponseDTO<StockDTO> getListStock(PageRequestDTO pageRequestDTO) {
        log.info("getListStock StockService................");
        log.info("Service pageRequest : " + pageRequestDTO);
        List<Stock> stockList = stockMapper.selectListStock(pageRequestDTO);
        //List<StockDTO> stockDTOList = stockList.stream().map(stock -> modelMapper.map(stock, StockDTO.class)).collect(Collectors.toList());
        //stockList.forEach(stock -> log.info("stock : " + stock.getMember().getName()));
        List<StockDTO> stockDTOList = new ArrayList<>();
        stockList.forEach(stock -> {
            MemberDTO memberDTO = MemberDTO.builder()
                    .id(stock.getMember().getId())
                    .name(stock.getMember().getName())
                    .email(stock.getMember().getEmail())
                    .phone(stock.getMember().getPhone())
                    .businessNumber(stock.getMember().getBusinessNumber()).build();

            WarehouseDTO warehouseDTO = WarehouseDTO.builder().
                    id(stock.getCell().getWarehouse().getId())
                    .name(stock.getCell().getWarehouse().getName())
                    .roadName(stock.getCell().getWarehouse().getRoadName())
                    .zipCode(stock.getCell().getWarehouse().getZipCode())
                    .lotNumber(stock.getCell().getWarehouse().getLotNumber()).build();

            CellDTO cellDTO = CellDTO.builder()
                    .id(stock.getCell().getId())
                    .warehouseDTO(warehouseDTO)
                    .capacity(stock.getCell().getCapacity()).build();

            ProductDTO productDTO = modelMapper.map(stock.getProduct(), ProductDTO.class);

            StockDTO stockDTO = StockDTO.builder()
                    .id(stock.getId())
                    .memberDTO(memberDTO)
                    .cellDTO(cellDTO)
                    .productDTO(productDTO)
                    .stockAmount(stock.getStockAmount())
                    .expirationDate(stock.getExpirationDate())
                    .manufacturingDate(stock.getManufacturingDate())
                    .regDate(stock.getRegDate())
                    .modDate(stock.getModDate()).build();

        stockDTOList.add(stockDTO);});
        //stockDTOList.forEach(stockDTO -> log.info("dto : " + stockDTO.getMemberDTO().getName()));

        int total = stockMapper.getStockCount(pageRequestDTO);
        return new PageResponseDTO<>(pageRequestDTO, stockDTOList, total);
    }

    @Override
    public StockDTO getStock(int id) {
        Optional<Stock> optionalStock = stockMapper.selectOneStock(id);
        StockDTO stockDTO;
        if(optionalStock.isPresent()) {
            stockDTO = modelMapper.map(optionalStock.get(), StockDTO.class);
            return stockDTO;
        }
        else {
            return StockDTO.builder().build();
        }

    }

    @Override
    public void modifyListStock() {
        stockMapper.updateList();
    }

    @Override
    public PageResponseDTO<TempStockDTO> getListTempStock(PageRequestDTO pageRequestDTO) {
        List<TempStock> tempStockList = stockMapper.selectListTempStock(pageRequestDTO);
        List<TempStockDTO> tempStockDTOList = new ArrayList<>();
        tempStockList.forEach(tempStock -> {
            MemberDTO memberDTO = MemberDTO.builder()
                    .id(tempStock.getMember().getId())
                    .name(tempStock.getMember().getName())
                    .email(tempStock.getMember().getEmail())
                    .phone(tempStock.getMember().getPhone())
                    .businessNumber(tempStock.getMember().getBusinessNumber()).build();

            WarehouseDTO warehouseDTO = WarehouseDTO.builder().
                    id(tempStock.getCell().getWarehouse().getId())
                    .name(tempStock.getCell().getWarehouse().getName())
                    .roadName(tempStock.getCell().getWarehouse().getRoadName())
                    .zipCode(tempStock.getCell().getWarehouse().getZipCode())
                    .lotNumber(tempStock.getCell().getWarehouse().getLotNumber()).build();

            CellDTO cellDTO = CellDTO.builder()
                    .id(tempStock.getCell().getId())
                    .warehouseDTO(warehouseDTO)
                    .capacity(tempStock.getCell().getCapacity()).build();

            ProductDTO productDTO = modelMapper.map(tempStock.getProduct(), ProductDTO.class);

            TempStockDTO tempStockDTO = TempStockDTO.builder()
                    .id(tempStock.getId())
                    .memberDTO(memberDTO)
                    .cellDTO(cellDTO)
                    .productDTO(productDTO)
                    .changeAmount(tempStock.getChangeAmount())
                    .expirationDate(tempStock.getExpirationDate())
                    .manufacturingDate(tempStock.getManufacturingDate())
                    .regDate(tempStock.getRegDate())
                    .tempStockState(tempStock.isTempStockState()).build();

            tempStockDTOList.add(tempStockDTO);});


        int total =stockMapper.getTempStockCount(pageRequestDTO);


        return new PageResponseDTO<>(pageRequestDTO, tempStockDTOList, total);
    }
}
