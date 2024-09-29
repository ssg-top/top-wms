package com.top.effitopia.controller;

import com.top.effitopia.dto.PageRequestDTO;
import com.top.effitopia.dto.PageResponseDTO;
import com.top.effitopia.dto.StockDTO;
import com.top.effitopia.dto.TempStockDTO;
import com.top.effitopia.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
    public void listStock(PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("listStock controller........");
        PageResponseDTO<StockDTO> pageResponseDTO = stockService.getListStock(pageRequestDTO);
        log.info("pageResponseDTO : " + pageResponseDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);


    }

    @PostMapping("/update")
    public String updateStock(){
        log.info("updateStock controller........");
        stockService.modifyListStock();
        return "redirect:/stocks/list";
    }

    @GetMapping("/list-tempstock")
    public void listTempStock(PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){
        log.info("listTempStock controller........");
        PageResponseDTO<TempStockDTO> pageResponseDTO = stockService.getListTempStock(pageRequestDTO);
        log.info("pageResponseDTO : " + pageResponseDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
    }


}
