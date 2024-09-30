package com.top.effitopia.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonthlyBalance {

    int year;
    int month;
    private double totalExpense;
    private double totalRevenue;
    private double netProfit;
    private double netProfitGrowthRate;
}
