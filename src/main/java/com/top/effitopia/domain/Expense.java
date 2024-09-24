package com.top.effitopia.domain;

import com.top.effitopia.enumeration.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Expense {

    private Integer id;
    private Warehouse warehouse;
    private ExpenseCategory category;
    private double amount;
    private String expenseDetails;
    private LocalDateTime expenseDate;
    private boolean isPaid;
    private boolean delFlag;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

}
