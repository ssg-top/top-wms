package com.top.effitopia.service;

import com.top.effitopia.domain.Stock;
import com.top.effitopia.domain.TempStock;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.StockDTO;
import com.top.effitopia.dto.TempStockDTO;
import com.top.effitopia.mapper.StockMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

        List<Stock> stockList = stockMapper.selectListStock(pageRequestDTO);
        List<StockDTO> stockDTOList = stockList.stream().map(stock -> modelMapper.map(stock, StockDTO.class)).collect(Collectors.toList());


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
        List<TempStockDTO> tempStockDTOList = tempStockList.stream().map(tempStock -> modelMapper.map(tempStock, TempStockDTO.class)).collect(Collectors.toList());

        int total =stockMapper.getTempStockCount(pageRequestDTO);

        return new PageResponseDTO<>(pageRequestDTO, tempStockDTOList, total);
    }
}
