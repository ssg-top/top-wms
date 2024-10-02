package com.top.effitopia.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockSearchCond {
    private String memberName;
    private String wareHouseName;
    private String productName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String bigCategory;
    private String midCategory;
    private String smallCategory;
    private int state =0;
}
