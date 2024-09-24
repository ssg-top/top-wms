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
public class StockDTO {
    private int	stockID;	            //재고ID
    private int	stockAmount;	        //재고수량
    private LocalDate manufacturingDate;//제조일자
    private LocalDate expirationDate;	//유통기한
    private LocalDate regDate;	        //재고생성일
    private int	memberID;	            //회원ID
    private int	cellID;	                //창고세부구역ID
    private int	productID;	            //상품ID
}
