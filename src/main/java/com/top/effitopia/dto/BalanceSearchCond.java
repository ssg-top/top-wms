package com.top.effitopia.dto;

import com.top.effitopia.enumeration.ExpenseCategory;
import com.top.effitopia.enumeration.RevenueCategory;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BalanceSearchCond {

    ExpenseCategory expenseCategory;
    RevenueCategory revenueCategory;
    Integer fromAmount;
    Integer toAmount;
    LocalDate fromDate;
    LocalDate toDate;
    Boolean paid;
    String username;
    Integer warehouseId;
}
