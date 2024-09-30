package com.top.effitopia.controller;

import com.top.effitopia.dto.*;
import com.top.effitopia.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 재고 Controller
 */
@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;



    @GetMapping("/list")
    public String listStock(@ModelAttribute("pageRequestDTO") PageRequestDTO<StockSearchCond> pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("listStock controller........");
        if (pageRequestDTO.getSearchCond() == null) {
            pageRequestDTO.setSearchCond(new StockSearchCond());
        }

        try {
            PageResponseDTO<StockDTO> pageResponseDTO = stockService.getListStock(pageRequestDTO);
            log.info("pageResponseDTO : " + pageResponseDTO);
            if (pageResponseDTO == null || pageResponseDTO.getDtoList() == null) {
                throw new RuntimeException("페이지 응답 데이터가 null입니다.");
            }

           //pageResponseDTO.getDtoList().forEach(stockDTO -> log.info("result : " + stockDTO.getMemberDTO()) );
            log.info("Controller pageRequest : " + pageRequestDTO);
            model.addAttribute("pageResponseDTO", pageResponseDTO);
            return "stock/list";
        } catch (Exception e) {
            log.error("Error in listStock: ", e);
            model.addAttribute("errorMessage", "재고 목록을 불러오는 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/";
        }

//        PageResponseDTO<StockDTO> pageResponseDTO = stockService.getListStock(pageRequestDTO);
//        log.info("pageResponseDTO : " + pageResponseDTO);
//        model.addAttribute("pageResponseDTO", pageResponseDTO);
//        return "stock/list";
    }


    @PostMapping("/update")
    public String updateStock(){
        log.info("updateStock controller........");
        stockService.modifyListStock();
        return "redirect:/stock/list";
    }

    @GetMapping("/list-tempstock")
    public void listTempStock(PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){
        log.info("listTempStock controller........");
        PageResponseDTO<TempStockDTO> pageResponseDTO = stockService.getListTempStock(pageRequestDTO);
        log.info("pageResponseDTO : " + pageResponseDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
    }


}
