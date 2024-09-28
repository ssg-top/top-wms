package com.top.effitopia.controller;

import com.top.effitopia.dto.StockCheckDTO;
import com.top.effitopia.service.StockCheckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
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
    public void listStockCheck() {
        log.info("listStockCheck StockCheckController.............");
    }

    @GetMapping("/read")
    public void readStockCheck() {
        log.info("readStockCheck StockCheckController.............");
    }

    @PostMapping("/modify")
    public String modifyStockCheck() {
        log.info("modifyStockCheck StockCheckController.............");
        return "redirect:/list";
    }

    @PostMapping("/remove")
    public String removeStockCheck() {
        log.info("removeStockCheck StockCheckController.............");
        return "redirect:/list";
    }

    @PostMapping("/apply")
    public String applyStockCheck() {
        log.info("applyStockCheck StockCheckController.............");
        return "redirect:/list";
    }
}
