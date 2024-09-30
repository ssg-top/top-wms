package com.top.effitopia.controller;

import com.top.effitopia.dto.*;
import com.top.effitopia.service.StockCheckService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 재고실사 Controller
 */
@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/stockcheck")
public class StockCheckController {

    private final StockCheckService stockCheckService;

    @PostMapping("/register")
    public String registerStockCheck(@Valid StockCheckDTO stockCheckDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("registerStockCheck StockCheckController.............");

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        stockCheckService.saveStockCheck(stockCheckDTO);
        return "redirect: /stockcheck/list";
    }

    @GetMapping("/list")
    public void listStockCheck(PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("listStockCheck StockCheckController.............");
        PageResponseDTO<StockCheckDTO> pageResponseDTO = stockCheckService.getListStockCheck(pageRequestDTO);
        log.info("pageResponseDTO : " + pageResponseDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);

    }

    @GetMapping("/read")
    public void readStockCheck(Id id, PageRequestDTO pageRequestDTO, Model model) {
        log.info("readStockCheck StockCheckController.............");
        StockCheckDTO stockCheckDTO = stockCheckService.getstockCheck(id.getId());
        model.addAttribute("stockCheck", stockCheckDTO);
    }

    @PostMapping("/modify")
    public String modifyStockCheck(@Valid StockCheckDTO stockCheckDTO, PageRequestDTO pageRequestDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("modifyStockCheck StockCheckController.............");
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
            return "redirect:/list";
        }

        stockCheckService.modifyStockCheck(stockCheckDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/list";
    }

    @PostMapping("/remove")
    public String removeStockCheck(Id id, PageRequestDTO pageRequestDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("removeStockCheck StockCheckController.............");
        stockCheckService.removeStockCheck(id.getId());
        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/list";
    }

    @PostMapping("/apply")
    public String applyStockCheck(PageRequestDTO pageRequestDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("applyStockCheck StockCheckController.............");
        //stockCheckService.applyListStockCheck();
        return "redirect:/list";
    }
}
