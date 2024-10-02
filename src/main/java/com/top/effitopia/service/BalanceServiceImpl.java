package com.top.effitopia.service;

import com.top.effitopia.domain.*;
import com.top.effitopia.dto.*;
import com.top.effitopia.enumeration.ExpenseCategory;
import com.top.effitopia.enumeration.RevenueCategory;
import com.top.effitopia.exception.BizException;
import com.top.effitopia.exception.ErrorCode;
import com.top.effitopia.mapper.BalanceMapper;
import com.top.effitopia.mapper.ExpenseMapper;
import com.top.effitopia.mapper.RevenueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final RevenueMapper revenueMapper;
    private final ExpenseMapper expenseMapper;
    private final BalanceMapper balanceMapper;

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
    public PageResponseDTO<ExpenseDTO> getExpenseList(PageRequestDTO<BalanceSearchCond> pageRequestDTO) {
        List<ExpenseDTO> dtoList = expenseMapper.selectAll(pageRequestDTO).stream()
                .map(expense -> ExpenseDTO.from(expense))
                .toList();
        int totalCount = expenseMapper.selectCount(pageRequestDTO);
        return new PageResponseDTO<>(pageRequestDTO, dtoList, totalCount);
    }

    @Override
    public PageResponseDTO<RevenueDTO> getRevenueList(PageRequestDTO<BalanceSearchCond> pageRequestDTO) {
        List<RevenueDTO> dtoList = revenueMapper.selectAll(pageRequestDTO).stream()
                .map(revenue -> RevenueDTO.from(revenue))
                .toList();
        int totalCount = revenueMapper.selectCount(pageRequestDTO);
        return new PageResponseDTO<>(pageRequestDTO, dtoList, totalCount);
    }

    @Override
    public boolean saveExpense(ExpenseDTO expenseDTO) {
        Expense expense = Expense.builder()
                .warehouse(Warehouse.builder().id(expenseDTO.getWarehouseId()).build())
                .category(expenseDTO.getCategory())
                .amount(expenseDTO.getAmount())
                .expenseDetails(expenseDTO.getExpenseDetails())
                .expenseDate(expenseDTO.getExpenseDate())
                .paid(expenseDTO.isPaid())
                .deleted(false)
                .build();
        return expenseMapper.insert(expense) == 1;
    }

    @Override
    public boolean saveRevenue(RevenueDTO revenueDTO) {
        Revenue revenue = Revenue.builder()
                .warehouse(Warehouse.builder().id(revenueDTO.getWarehouseId()).build())
                .member(Member.builder().id(revenueDTO.getMemberId()).build())
                .category(revenueDTO.getCategory())
                .amount(revenueDTO.getAmount())
                .revenueDetails(revenueDTO.getRevenueDetails())
                .requestDate(revenueDTO.getRequestDate())
                .paid(revenueDTO.isPaid())
                .deleted(false)
                .build();
        return revenueMapper.insert(revenue) == 1;
    }

    @Override
    public boolean modifyExpense(ExpenseDTO expenseDTO) {
        Expense expense = Expense.builder()
                .id(expenseDTO.getId())
                .category(expenseDTO.getCategory())
                .amount(expenseDTO.getAmount())
                .expenseDetails(expenseDTO.getExpenseDetails())
                .expenseDate(expenseDTO.getExpenseDate())
                .paid(expenseDTO.isPaid())
                .build();
        return expenseMapper.update(expense) == 1;
    }

    @Override
    public boolean modifyRevenue(RevenueDTO revenueDTO) {
        Revenue revenue = Revenue.builder()
                .id(revenueDTO.getId())
                .warehouse(Warehouse.builder().id(revenueDTO.getWarehouseId()).build())
                .category(revenueDTO.getCategory())
                .amount(revenueDTO.getAmount())
                .revenueDetails(revenueDTO.getRevenueDetails())
                .requestDate(revenueDTO.getRequestDate())
                .build();
        return revenueMapper.update(revenue) == 1;
    }

    @Override
    public boolean modifyRevenuePaymentInfo(RevenueDTO revenueDTO) {
        Revenue paymentInfo = revenueDTO.getPaymentInfo();
        return revenueMapper.updatePaymentInfo(paymentInfo) == 1;
    }

    @Override
    public boolean removeExpense(int id) {
        return expenseMapper.delete(id) == 1;
    }

    @Override
    public boolean removeRevenue(int id) {
        return revenueMapper.delete(id) == 1;
    }

    @Override
    public Map<String, String> getRevenueCategoryList() {
        return RevenueCategory.CATEGORY_MAP;
    }

    @Override
    public Map<String, String> getExpenseCategoryList() {
        return ExpenseCategory.CATEGORY_MAP;
    }

    @Override
    public Optional<AnnualBalance> getAnnualBalance(int year) {
        return balanceMapper.getAnnualBalance(year);
    }

    @Override
    public List<AnnualBalance> getAnnualBalances() {
        return balanceMapper.getAnnualBalances();
    }

    @Override
    public List<MonthlyBalance> getMonthlyBalances(int year) {
        return balanceMapper.getMonthlyBalances(year);
    }
}
