package com.top.effitopia.service;

import com.top.effitopia.domain.Stock;
import com.top.effitopia.domain.StockCheck;
import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.StockCheckDTO;
import com.top.effitopia.dto.StockDTO;
import com.top.effitopia.mapper.StockCheckMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        List<StockCheck> stockCheckList = stockCheckDTOList.stream().map(stockCheckDTO -> modelMapper.map(stockCheckDTO, StockCheck.class)).collect(Collectors.toList());
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
    public PageResponseDTO<StockCheckDTO> getListStockCheck(PageRequestDTO pageRequestDTO) {
        List<StockCheck> stockCheckList = stockCheckMapper.selectListStockCheck(pageRequestDTO);
        List<StockCheckDTO> stockCheckDTOList = stockCheckList.stream().map(stockcheck -> modelMapper.map(stockcheck, StockCheckDTO.class)).collect(Collectors.toList());


        int total = stockCheckMapper.getStockCheckCount(pageRequestDTO);
        return new PageResponseDTO<>(pageRequestDTO, stockCheckDTOList, total);
    }

    @Override
    public void applyListStockCheck(List<StockCheckDTO> stockCheckDTOList) {
        List<StockCheck> stockCheckList = stockCheckDTOList.stream().map(stockCheckDTO-> modelMapper.map(stockCheckDTO, StockCheck.class)).collect(Collectors.toList());
        stockCheckMapper.applyList(stockCheckList);
    }
}
