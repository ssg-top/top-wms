package com.top.effitopia.service;


import com.top.effitopia.domain.AnnualBalance;
import com.top.effitopia.domain.MonthlyBalance;
import com.top.effitopia.dto.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BalanceService {

    ExpenseDTO getExpense(int id);
    RevenueDTO getRevenue(int id);

    PageResponseDTO<RevenueDTO> getRevenueList(PageRequestDTO<BalanceSearchCond> pageRequestDTO);
    PageResponseDTO<ExpenseDTO> getExpenseList(PageRequestDTO<BalanceSearchCond> pageRequestDTO);

    boolean saveExpense(ExpenseDTO expenseDTO);
    boolean saveRevenue(RevenueDTO revenueDTO);

    boolean modifyExpense(ExpenseDTO expenseDTO);
    boolean modifyRevenue(RevenueDTO revenueDTO);

    boolean modifyRevenuePaymentInfo(RevenueDTO revenueDTO);

    boolean removeExpense(int id);
    boolean removeRevenue(int id);

    Map<String, String> getRevenueCategoryList();
    Map<String, String> getExpenseCategoryList();

    Optional<AnnualBalance> getAnnualBalance(int year);
    List<AnnualBalance> getAnnualBalances();
    List<MonthlyBalance> getMonthlyBalances(int year);

}
