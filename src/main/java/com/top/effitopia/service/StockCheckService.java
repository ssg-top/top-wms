package com.top.effitopia.service;

import com.top.effitopia.dto.*;

import java.util.List;

/**
 * 재고실사 Service Interface
 */
public interface StockCheckService {

    public void saveStockCheck(StockCheckDTO stockCheckDTO);
    public void modifyStockCheck(StockCheckDTO stockCheckDTO);
    public void removeStockCheck(int id);
    public StockCheckDTO getstockCheck(int id);


    public void saveListStockCheck(List<StockCheckDTO> stockCheckDTOList);
    public void modifyListStockCheck(List<StockCheckDTO> stockCheckDTOList);
    public void removeListStockCheck(List<Integer> idList);
    public PageResponseDTO<StockCheckDTO> getListStockCheck(PageRequestDTO<StockSearchCond> pageRequestDTO);

    public void applyListStockCheck(List<StockCheckDTO> stockCheckDTOList);

}
