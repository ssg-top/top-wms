package com.top.effitopia.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockVO {
    private int stockID;                //재고ID
    private int stockAmount;            //재고 수량
    private LocalDate manufacturingDate;//제조일자
    private LocalDate expirationDate;   //생성일자
    private LocalDate regDate;          //생성일자
    private LocalDate modDate;          //변동일자
    private int	memberID;               //회원ID
    private int	cellID;                 //창고세부구역ID
    private int	productID;              //상품ID
}
