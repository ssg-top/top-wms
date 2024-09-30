package com.top.effitopia.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnualBalance {

    int year;
    private double totalExpense;
    private double totalRevenue;
    private double netProfit;
    private double netProfitGrowthRate;
}
