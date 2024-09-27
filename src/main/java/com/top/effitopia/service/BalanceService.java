package com.top.effitopia.service;


import com.top.effitopia.dto.ExpenseDTO;
import com.top.effitopia.dto.RevenueDTO;

import java.util.List;

public interface BalanceService {

    ExpenseDTO getExpense(int id);
    RevenueDTO getRevenue(int id);

    List<ExpenseDTO> getExpenseList();
    List<RevenueDTO> getRevenueList();

    boolean saveExpense(ExpenseDTO expenseDTO);
    boolean saveRevenue(RevenueDTO revenueDTO);

    boolean modifyExpense(ExpenseDTO expenseDTO);
    boolean modifyRevenue(RevenueDTO revenueDTO);

    boolean removeExpense(int id);
    boolean removeRevenue(int id);





}
