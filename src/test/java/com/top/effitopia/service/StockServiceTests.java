package com.top.effitopia.service;

import com.top.effitopia.dto.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class StockServiceTests {

    @Autowired
    private StockService stockService;

    @Test
    public void getListStockTest() {
        StockSearchCond stockSearchCond = StockSearchCond.builder().memberName("정사업자").wareHouseName("삼성").productName("요구르트").build();

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().searchCond(stockSearchCond).build();
        log.info(pageRequestDTO);
        log.info(pageRequestDTO.getSearchCond());
        PageResponseDTO<StockDTO> pageResponseDTO = stockService.getListStock(pageRequestDTO);
        log.info("response : " + pageResponseDTO);
        pageResponseDTO.getDtoList().forEach(stockDTO -> log.info("검색어 필터 Test : " + stockDTO));

    }


    @Test
    public void getListTempStock() {
//        public PageResponseDTO<TempStockDTO> getListTempStock(PageRequestDTO pageRequestDTO);
    }





}
