package com.top.effitopia.service;


import com.top.effitopia.domain.AnnualBalance;
import com.top.effitopia.domain.MonthlyBalance;
import com.top.effitopia.dto.*;

import java.util.List;

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

    List<AnnualBalance> getAnnualBalances();
    List<MonthlyBalance> getMonthlyBalances(int year);





}
