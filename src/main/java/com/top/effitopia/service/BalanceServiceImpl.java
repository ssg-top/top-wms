package com.top.effitopia.service;

import com.top.effitopia.domain.Expense;
import com.top.effitopia.domain.Revenue;
import com.top.effitopia.dto.ExpenseDTO;
import com.top.effitopia.dto.RevenueDTO;
import com.top.effitopia.exception.BizException;
import com.top.effitopia.exception.ErrorCode;
import com.top.effitopia.mapper.ExpenseMapper;
import com.top.effitopia.mapper.RevenueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final RevenueMapper revenueMapper;
    private final ExpenseMapper expenseMapper;

    @Override
    public ExpenseDTO getExpense(int id) {
        Expense expense = expenseMapper.selectOne(id)
                .orElseThrow(() -> new BizException(ErrorCode.NOT_EXISTS_EXPENSE));
        return ExpenseDTO.from(expense);
    }

    @Override
    public RevenueDTO getRevenue(int id) {
        Revenue revenue = revenueMapper.selectOne(id)
                .orElseThrow(() -> new BizException(ErrorCode.NOT_EXISTS_REVENUE));
        return RevenueDTO.from(revenue);
    }

    @Override
    public List<ExpenseDTO> getExpenseList() {
        return expenseMapper.selectAll().stream()
                .map(expense -> ExpenseDTO.from(expense))
                .toList();
    }

    @Override
    public List<RevenueDTO> getRevenueList() {
        return revenueMapper.selectAll().stream()
                .map(revenue -> RevenueDTO.from(revenue))
                .toList();
    }

    @Override
    public boolean saveExpense(ExpenseDTO expenseDTO) {
        return false;
    }

    @Override
    public boolean saveRevenue(RevenueDTO revenueDTO) {
        return false;
    }

    @Override
    public boolean modifyExpense(ExpenseDTO expenseDTO) {
        return false;
    }

    @Override
    public boolean modifyRevenue(RevenueDTO revenueDTO) {
        return false;
    }

    @Override
    public boolean removeExpense(int id) {
        return false;
    }

    @Override
    public boolean removeRevenue(int id) {
        return false;
    }
}
