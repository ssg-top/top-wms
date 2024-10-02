package com.top.effitopia.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.top.effitopia.domain.AnnualBalance;
import com.top.effitopia.domain.MonthlyBalance;
import com.top.effitopia.dto.MemberSearchCond;
import com.top.effitopia.dto.WarehouseUtilizationDTO;
import com.top.effitopia.enumeration.MemberStatus;
import com.top.effitopia.service.AuthService;
import com.top.effitopia.service.BalanceService;
import com.top.effitopia.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class DashBoardController {

    private final AuthService authService;
    private final BalanceService balanceService;
    private final WarehouseService warehouseService;
    private final ObjectMapper objectMapper;

    @GetMapping("/")
    public String index(Model model) throws JsonProcessingException {
        AnnualBalance annualBalance = balanceService.getAnnualBalance(2024)
                .orElseThrow(RuntimeException::new);
        int totalMemberCount = authService.getCount(new MemberSearchCond());
        int requestRegisterMemberCount = authService.getCount(MemberSearchCond.builder().status(MemberStatus.REGISTER_REQUEST).build());
        double totalUtilizationAverage = warehouseService.getTotalUtilizationAverage();
        List<WarehouseUtilizationDTO> warehouseUtilizationList = warehouseService.getWarehouseUtilizationList();
        String jsonUtilizationList = objectMapper.writeValueAsString(warehouseUtilizationList);
        List<MonthlyBalance> monthlyBalanceList = balanceService.getMonthlyBalances(2024);
        model.addAttribute("annualBalance", annualBalance);
        model.addAttribute("totalMemberCount", totalMemberCount);
        model.addAttribute("requestRegisterMemberCount", requestRegisterMemberCount);
        model.addAttribute("totalUtilizationAverage", totalUtilizationAverage);
        model.addAttribute("jsonUtilizationList", jsonUtilizationList);
        model.addAttribute("monthlyBalances", monthlyBalanceList);
        warehouseUtilizationList.forEach(log::info);
        return "dashboard/index";
    }
}
