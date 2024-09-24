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
public class StockCheckDTO {
    private int	stockCheckID;	    //재고실사ID
    private boolean	checkStatus;	//재고실사 상태
    private String	checkComment;	//재고실사 설명
    private int	checkAmount;	    //실사 수량
    private LocalDate	checkDate;	//재고실사 일시
    private LocalDate applyDate;	//재고실사 반영 일시
    private int	stockID;	        //재고ID
    private int	cellID;	            //창고세부구역ID
}
