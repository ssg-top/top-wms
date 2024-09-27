package com.top.effitopia.dto;

import com.top.effitopia.domain.Expense;
import com.top.effitopia.enumeration.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {

    private Integer id;
    private int warehouseId;
    private String warehouseCode;
    private ExpenseCategory category;
    private double amount;
    private String expenseDetails;
    private LocalDateTime expenseDate;
    private boolean isPaid;

    public static ExpenseDTO from(Expense expense) {
        return ExpenseDTO.builder()
                .id(expense.getId())
                .warehouseId(expense.getWarehouse().getId())
                .warehouseCode(expense.getWarehouse().getCode())
                .category(expense.getCategory())
                .amount(expense.getAmount())
                .expenseDetails(expense.getExpenseDetails())
                .expenseDate(expense.getExpenseDate())
                .isPaid(expense.isPaid())
                .build();
    }
}
