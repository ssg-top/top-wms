package com.top.effitopia.service;

import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.StockDTO;
import com.top.effitopia.dto.TempStockDTO;

import java.util.List;

/**
 * 재고 Service Interface
 */
public interface StockService {
    
    public PageResponseDTO<StockDTO> getListStock(PageRequestDTO pageRequestDTO);

    public StockDTO getStock(int id);

    /**
     * 임시재고를 재고로 반영
     */
    public void modifyListStock();

    public PageResponseDTO<TempStockDTO> getListTempStock(PageRequestDTO pageRequestDTO);
}
