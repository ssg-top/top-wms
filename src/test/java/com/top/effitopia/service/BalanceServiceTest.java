package com.top.effitopia.service;

import com.top.effitopia.domain.AnnualBalance;
import com.top.effitopia.domain.Expense;
import com.top.effitopia.domain.MonthlyBalance;
import com.top.effitopia.dto.*;
import com.top.effitopia.enumeration.ExpenseCategory;
import com.top.effitopia.enumeration.RevenueCategory;
import com.top.effitopia.mapper.ExpenseMapper;
import com.top.effitopia.mapper.RevenueMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
class BalanceServiceTest {

    @Autowired
    BalanceService balanceService;
    @Autowired
    RevenueMapper revenueMapper;
    @Autowired
    ExpenseMapper expenseMapper;

    Expense newExpense;

    @Test
    void getExpense() {
        ExpenseDTO expense = balanceService.getExpense(1);
        log.info(expense);
    }

    @Test
    void getRevenue() {
        RevenueDTO revenue = balanceService.getRevenue(1);
        log.info(revenue);
    }

    @Test
    void getExpenseList() {
        PageResponseDTO<ExpenseDTO> expenseList = balanceService.getExpenseList(
                PageRequestDTO.<BalanceSearchCond>builder()
                        .searchCond(new BalanceSearchCond())
                        .build());
        expenseList.getDtoList()
                .forEach(expense -> log.info(expense));
    }

    @Test
    void getRevenueList() {
        PageResponseDTO<RevenueDTO> revenueList = balanceService.getRevenueList(
                PageRequestDTO.<BalanceSearchCond>builder()
                        .searchCond(new BalanceSearchCond())
                        .build());
        revenueList.getDtoList()
                .forEach(revenue -> log.info(revenue));

    }

    @Test
    void saveExpense() {
        ExpenseDTO expenseDTO = ExpenseDTO.builder()
                .warehouseId(1)
                .category(ExpenseCategory.PERSONAL_COST)
                .amount(111031500)
                .expenseDate(LocalDateTime.of(2023, 4, 1, 0,0,0))
                .expenseDetails("")
                .paid(false)
                .build();
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
        balanceService.saveExpense(expenseDTO);
    }

    @Test
    void saveRevenue() {
        RevenueDTO revenueDTO = RevenueDTO.builder()
                .warehouseId(1)
                .memberId(1)
                .category(RevenueCategory.FREIGHT_CHARGE)
                .amount(142266500)
                .revenueDetails("")
                .requestDate(LocalDateTime.of(2023, 4, 1, 0,0,0))
                .build();
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);
        balanceService.saveRevenue(revenueDTO);

    }

    @Test
    void getAnnualBalances() {
        List<AnnualBalance> annualBalances = balanceService.getAnnualBalances();
        Assertions.assertNotNull(annualBalances);
        annualBalances.stream()
                .forEach(log::info);
    }

    @Test
    void getMonthlyBalances() {
        List<MonthlyBalance> monthlyBalances = balanceService.getMonthlyBalances(2024);
        Assertions.assertNotNull(monthlyBalances);
        monthlyBalances.stream()
                .forEach(log::info);
    }
}
