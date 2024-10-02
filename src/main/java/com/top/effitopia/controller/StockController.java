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


//, @ModelAttribute("searchCond") StockSearchCond stockSearchCond,
    @GetMapping("/list")
    public String listStock(@Valid @ModelAttribute("pageRequestDTO") PageRequestDTO<StockSearchCond> pageRequestDTO, @ModelAttribute("searchCond") StockSearchCond stockSearchCond, BindingResult bindingResult, Model model) {
        log.info("listStock controller........");

        if(bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            return "redirect:/";
        }

        pageRequestDTO.setSearchCond(stockSearchCond);
        PageResponseDTO<StockDTO> pageResponseDTO = stockService.getListStock(pageRequestDTO);
        log.info("pageResponseDTO : " + pageResponseDTO);
        log.info("Controller pageRequest : " + pageRequestDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        log.info("searcon : " + stockSearchCond);
        return "stock/list";
    }


    @GetMapping("/update")
    public String updateStock(Model model){
        log.info("updateStock controller........");
        try {
            stockService.modifyListStock();
            model.addAttribute("message", "마감이 성공적으로 처리되었습니다.");
        } catch (Exception e) {
            log.error("Error in updateStock: ", e);
            model.addAttribute("error", "마감 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "stock/list-tempstock";
    }

    @GetMapping("/list-tempstock")
    public String listTempStock(@Valid @ModelAttribute("pageRequestDTO") PageRequestDTO<StockSearchCond> pageRequestDTO, @ModelAttribute("searchCond") StockSearchCond stockSearchCond, BindingResult bindingResult, Model model){
        log.info("listTempStock controller........");

        if(bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            return "redirect:/";
        }

        pageRequestDTO.setSearchCond(stockSearchCond);
        PageResponseDTO<TempStockDTO> pageResponseDTO = stockService.getListTempStock(pageRequestDTO);
        log.info("Controller pageRequest : " + pageRequestDTO);
        log.info("searcon : " + stockSearchCond);
        pageResponseDTO.getDtoList().forEach(tempStockDTO -> log.info("tempstockDTO : " + tempStockDTO.isTempStockState()));
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        return "stock/list-tempstock";
    }


}
