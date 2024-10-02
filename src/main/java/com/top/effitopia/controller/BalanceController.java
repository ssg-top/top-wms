package com.top.effitopia.controller;

import com.top.effitopia.domain.AnnualBalance;
import com.top.effitopia.domain.MonthlyBalance;
import com.top.effitopia.dto.*;
import com.top.effitopia.enumeration.MemberStatus;
import com.top.effitopia.service.BalanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping("/revenue-list")
    public void list(@Valid PageRequestDTO<BalanceSearchCond> pageRequestDTO, BalanceSearchCond searchCond, Model model) {
        log.info("검색 조건: {}", searchCond);
        log.info("페이징 조건: {}", pageRequestDTO);
        pageRequestDTO.setSearchCond(searchCond);
        PageResponseDTO<RevenueDTO> pageResponseDTO = balanceService.getRevenueList(pageRequestDTO);
        Map<String, String> categories = balanceService.getRevenueCategoryList();
        model.addAttribute("responseDTO", pageResponseDTO);
        model.addAttribute("searchCond", searchCond);
        model.addAttribute("categories", categories);
    }

    @GetMapping("/total")
    public void total(Model model, Integer year) {
        List<AnnualBalance> annualBalances = balanceService.getAnnualBalances();
        model.addAttribute("annualBalances", annualBalances);
    }
}
